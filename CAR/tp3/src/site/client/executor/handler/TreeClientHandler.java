package site.client.executor.handler;

import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import site.server.bean.tree.TreeNode;
import site.shared.behavior.BehaviorManager;
import site.shared.logger.LoggerFactory;

public class TreeClientHandler implements ClientHandler {

	private static final Logger LOGGER = LoggerFactory
			.create(TreeClientHandler.class);

	@Override
	public void execute(String[] args)
			throws RemoteException, InterruptedException, NotBoundException, UnknownHostException {
		// final TreeNode node1 = (TreeNode)
		// LocateRegistry.getRegistry(serverAddress, 1099)
		// .lookup("node1");
		// final TreeNode node2 = (TreeNode)
		// LocateRegistry.getRegistry(serverAddress, 1099)
		// .lookup("node2");
		// final TreeNode node3 = (TreeNode)
		// LocateRegistry.getRegistry(serverAddress, 1099)
		// .lookup("node3");
		// final TreeNode node4 = (TreeNode)
		// LocateRegistry.getRegistry(serverAddress, 1099)
		// .lookup("node4");
		// final TreeNode node5 = (TreeNode)
		// LocateRegistry.getRegistry(serverAddress, 1099)
		// .lookup("node5");
		// final TreeNode node6 = (TreeNode)
		// LocateRegistry.getRegistry(serverAddress, 1099)
		// .lookup("node6");

		final Registry registry = LocateRegistry.getRegistry(args[1],
				1099);
		final String[] keys = registry.list();
		final List<TreeNode> nodes = new LinkedList<TreeNode>();
		for (final String key : keys) {
			final TreeNode nodeToAdd = (TreeNode) registry.lookup(key);
			nodes.add(nodeToAdd);
		}
		final TreeNode[] nodesArray = nodes.toArray(new TreeNode[nodes.size()]);
		nodesArray[0].setSons(nodesArray[1], nodesArray[4]);
		nodesArray[1].setSons(nodesArray[2], nodesArray[3]);
		nodesArray[4].setSons(nodesArray[5]);

		final BehaviorManager<MessageSendingProcessParameters> messageSendingProcessManager = new BehaviorManager<MessageSendingProcessParameters>();
		messageSendingProcessManager.addBehavior(new SequentialMessageSendingProcessFromTheRootNode());
		messageSendingProcessManager.addBehavior(new SequentialMessageSendingProcessFromAnyNode());
		messageSendingProcessManager.addBehavior(new ConcurrentMessageSendingProcessFromTheRootNode());
		messageSendingProcessManager.addBehavior(new ConcurrentMessageSendingProcessFromAnyNode());
		
		final MessageSendingProcessParameters parameters = new MessageSendingProcessParameters(args, nodesArray);
		messageSendingProcessManager.execute(parameters);

		// Sequential data sending from the root node
//		LOGGER.info("**** SENDING A MESSAGE SEQUENTIALLY FROM THE ROOT NODE ****");
//		messageSendingManager
//				.setMessageSendingMethod(new SequentialMessageSendingFromTheRootNode());
//		messageSendingManager.resetDatas(nodesArray);
//		messageSendingManager.fullMessageSendingProcess(message, nodesArray[0],
//				superPrinter, nodesArray);
//		messageSendingManager.resetDatas(nodesArray);

		// // Sequential data sending from any node
		// LOGGER.info("\n**** SENDING A MESSAGE SEQUENTIALLY FROM ANY NODE ****");
		// messageSendingManager
		// .setMessageSendingMethod(new SequentialMessageSendingFromAnyNode());
		// messageSendingManager.resetDatas(nodesArray);
		// messageSendingManager.fullMessageSendingProcess(message,
		// nodesArray[4],
		// superPrinter, nodesArray);
		// messageSendingManager.resetDatas(nodesArray);
		//
		// // Concurrent data sending from the root node
		// LOGGER.info("\n**** SENDING A MESSAGE SIMULTANEOUSLY FROM THE ROOT NODE ****");
		// messageSendingManager
		// .setMessageSendingMethod(new
		// ConcurrentMessageSendingFromTheRootNode());
		// messageSendingManager.resetDatas(nodesArray);
		// messageSendingManager.fullMessageSendingProcess(message,
		// nodesArray[0],
		// superPrinter, nodesArray);
		// messageSendingManager.resetDatas(nodesArray);
		//
		// // Data sending from any node
		// LOGGER.info("\n**** SENDING A MESSAGE SIMULTANEOUSLY FROM ANY NODE ****\n");
		// messageSendingManager
		// .setMessageSendingMethod(new ConcurrentMessageSendingFromAnyNode());
		// messageSendingManager.resetDatas(nodesArray);
		// messageSendingManager.fullMessageSendingProcess(message,
		// nodesArray[4],
		// superPrinter, nodesArray);
		// messageSendingManager.resetDatas(nodesArray);
	}

}
