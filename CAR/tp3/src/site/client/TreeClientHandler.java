package site.client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.logging.Logger;

import site.server.SuperPrinter;
import site.server.bean.tree.TreeNode;
import site.server.sending.tree.TreeMessageSendingManager;
import site.server.sending.tree.concurrent.ConcurrentMessageSendingFromAnyNode;
import site.server.sending.tree.concurrent.ConcurrentMessageSendingFromTheRootNode;
import site.server.sending.tree.sequential.SequentialMessageSendingFromAnyNode;
import site.server.sending.tree.sequential.SequentialMessageSendingFromTheRootNode;
import site.shared.LoggerFactory;

public class TreeClientHandler implements ClientHandler {

	private static final Logger LOGGER = LoggerFactory.create(Client.class);

	@Override
	public void execute(String serverAddress, String message) throws RemoteException, InterruptedException,
			NotBoundException {
		final TreeNode node1 = (TreeNode) LocateRegistry.getRegistry(serverAddress, 1099)
				.lookup("node1");
		final TreeNode node2 = (TreeNode) LocateRegistry.getRegistry(serverAddress, 1099)
				.lookup("node2");
		final TreeNode node3 = (TreeNode) LocateRegistry.getRegistry(serverAddress, 1099)
				.lookup("node3");
		final TreeNode node4 = (TreeNode) LocateRegistry.getRegistry(serverAddress, 1099)
				.lookup("node4");
		final TreeNode node5 = (TreeNode) LocateRegistry.getRegistry(serverAddress, 1099)
				.lookup("node5");
		final TreeNode node6 = (TreeNode) LocateRegistry.getRegistry(serverAddress, 1099)
				.lookup("node6");

		node1.setSons(node2, node5);
		node2.setSons(node3, node4);
		node5.setSons(node6);

		final TreeNode[] nodes = { node1, node2, node3, node4, node5, node6 };

		// Instanciation of the data to send, the node printer and the message
		// sending manager
		final SuperPrinter superPrinter = new SuperPrinter();
		final TreeMessageSendingManager messageSendingManager = new TreeMessageSendingManager();

		// Sequential data sending from the root node
		LOGGER.info("\n**** SENDING A MESSAGE SEQUENTIALLY FROM THE ROOT NODE ****");
		messageSendingManager
				.setMessageSendingMethod(new SequentialMessageSendingFromTheRootNode());
		messageSendingManager.fullMessageSendingProcess(message, node1,
				superPrinter, nodes);
		messageSendingManager.resetDatas(nodes);

		// Sequential data sending from any node
		LOGGER.info("\n**** SENDING A MESSAGE SEQUENTIALLY FROM ANY NODE ****");
		messageSendingManager
				.setMessageSendingMethod(new SequentialMessageSendingFromAnyNode());
		messageSendingManager.fullMessageSendingProcess(message, node5,
				superPrinter, nodes);
		messageSendingManager.resetDatas(nodes);

		// Concurrent data sending from the root node
		LOGGER.info("\n**** SENDING A MESSAGE SIMULTANEOUSLY FROM THE ROOT NODE ****");
		messageSendingManager
				.setMessageSendingMethod(new ConcurrentMessageSendingFromTheRootNode());
		messageSendingManager.fullMessageSendingProcess(message, node1,
				superPrinter, nodes);
		messageSendingManager.resetDatas(nodes);

		// Data sending from any node
		LOGGER.info("\n**** SENDING A MESSAGE SIMULTANEOUSLY FROM ANY NODE ****\n");
		messageSendingManager
				.setMessageSendingMethod(new ConcurrentMessageSendingFromAnyNode());
		messageSendingManager.fullMessageSendingProcess(message, node5,
				superPrinter, nodes);
		messageSendingManager.resetDatas(nodes);
	}

}
