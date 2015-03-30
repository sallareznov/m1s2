package site.client.executor.handler;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import site.client.reader.SiteReader;
import site.server.bean.tree.TreeNode;
import site.shared.behavior.BehaviorManager;

public class TreeClientHandler implements ClientHandler {

	@Override
	public void execute(SiteReader reader, String[] args)
			throws InterruptedException, NotBoundException, IOException {
		final Registry registry = LocateRegistry.getRegistry(args[1],
				1099);
		final String[] keys = registry.list();
		final List<TreeNode> nodes = new LinkedList<TreeNode>();
		for (final String key : keys) {
			final TreeNode nodeToAdd = (TreeNode) registry.lookup(key);
			nodes.add(nodeToAdd);
		}
		
		final TreeNode[] nodesArray = nodes.toArray(new TreeNode[nodes.size()]);
		final Map<Integer, Integer[]> nodesToSons = reader.getNodesFromFile("test.txt");
		for (final Entry<Integer, Integer[]> entry : nodesToSons.entrySet()) {
			System.out.println(entry.getKey());
			final TreeNode[] sons = new TreeNode[entry.getValue().length];
			for (int i = 0 ; i < entry.getValue().length ; i++) {
				sons[i] = nodesArray[entry.getValue()[i] - 1];
			}
			nodesArray[entry.getKey() - 1].setSons(sons);
		}

		final BehaviorManager<MessageSendingProcessParameters> messageSendingProcessManager = new BehaviorManager<MessageSendingProcessParameters>();
		messageSendingProcessManager.addBehavior(new SequentialMessageSendingProcessFromTheRootNode());
		messageSendingProcessManager.addBehavior(new SequentialMessageSendingProcessFromAnyNode());
		messageSendingProcessManager.addBehavior(new ConcurrentMessageSendingProcessFromTheRootNode());
		messageSendingProcessManager.addBehavior(new ConcurrentMessageSendingProcessFromAnyNode());
		
		final MessageSendingProcessParameters parameters = new MessageSendingProcessParameters(args, nodesArray);
		messageSendingProcessManager.execute(parameters);
	}

}
