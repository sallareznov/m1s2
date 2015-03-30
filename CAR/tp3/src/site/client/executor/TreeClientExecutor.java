package site.client.executor;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import site.client.executor.handler.ConcurrentMessageSendingProcessFromAnyNode;
import site.client.executor.handler.ConcurrentMessageSendingProcessFromTheRootNode;
import site.client.executor.handler.MessageSendingProcessParameters;
import site.client.executor.handler.SequentialMessageSendingProcessFromAnyNode;
import site.client.executor.handler.SequentialMessageSendingProcessFromTheRootNode;
import site.server.bean.tree.TreeNode;
import site.shared.behavior.BehaviorManager;

public class TreeClientExecutor extends AbstractClientExecutor {

	public TreeClientExecutor() {
		super("TREE");
	}

	@Override
	public void execute(ClientExecutorParameters parameters)
			throws InterruptedException, NotBoundException, IOException {
		final String[] args = parameters.getArgs();
		final Registry registry = LocateRegistry.getRegistry(args[1], 1099);
		final String[] keys = registry.list();
		final List<TreeNode> nodes = new LinkedList<TreeNode>();
		for (final String key : keys) {
			final TreeNode nodeToAdd = (TreeNode) registry.lookup(key);
			nodes.add(nodeToAdd);
		}

		final TreeNode[] nodesArray = nodes.toArray(new TreeNode[nodes.size()]);
		final Map<Integer, Integer[]> nodesToSons = parameters.getNodesToSons();
		for (final Entry<Integer, Integer[]> entry : nodesToSons.entrySet()) {
			final TreeNode[] sons = new TreeNode[entry.getValue().length];
			for (int i = 0; i < entry.getValue().length; i++) {
				sons[i] = nodesArray[entry.getValue()[i] - 1];
			}
			nodesArray[entry.getKey() - 1].setSons(sons);
		}

		final BehaviorManager<MessageSendingProcessParameters> messageSendingProcessManager = new BehaviorManager<MessageSendingProcessParameters>();
		messageSendingProcessManager
				.addBehavior(new SequentialMessageSendingProcessFromTheRootNode());
		messageSendingProcessManager
				.addBehavior(new SequentialMessageSendingProcessFromAnyNode());
		messageSendingProcessManager
				.addBehavior(new ConcurrentMessageSendingProcessFromTheRootNode());
		messageSendingProcessManager
				.addBehavior(new ConcurrentMessageSendingProcessFromAnyNode());

		final MessageSendingProcessParameters messageSendingProcessParameters = new MessageSendingProcessParameters(
				args, nodesArray);
		messageSendingProcessManager.execute(messageSendingProcessParameters);
	}

}
