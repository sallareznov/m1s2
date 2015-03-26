package site.client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.Arrays;
import java.util.logging.Logger;

import site.server.SuperPrinter;
import site.server.VisitedNodesManager;
import site.server.bean.graph.GraphNode;
import site.server.sending.graph.BFSMessageSending;
import site.shared.LoggerFactory;

public class GraphClient {

	private static final Logger LOGGER = LoggerFactory.create(GlobalClient.class);

	public void execute() throws RemoteException, InterruptedException,
			NotBoundException {
		final GraphNode node1 = (GraphNode) LocateRegistry.getRegistry(1099)
				.lookup("node1");
		final GraphNode node2 = (GraphNode) LocateRegistry.getRegistry(1099)
				.lookup("node2");
		final GraphNode node3 = (GraphNode) LocateRegistry.getRegistry(1099)
				.lookup("node3");
		final GraphNode node4 = (GraphNode) LocateRegistry.getRegistry(1099)
				.lookup("node4");
		final GraphNode node5 = (GraphNode) LocateRegistry.getRegistry(1099)
				.lookup("node5");
		final GraphNode node6 = (GraphNode) LocateRegistry.getRegistry(1099)
				.lookup("node6");

		node1.setNeighbors(Arrays.asList(node2, node5));
		node2.setNeighbors(Arrays.asList(node3, node4));
		node5.setNeighbors(Arrays.asList(node6));

		final GraphNode[] nodes = { node1, node2, node3, node4, node5, node6 };

		// Instanciation of the data to send, the node printer and the message
		// sending manager
		final String message = "RMI rocks !";
		final SuperPrinter superPrinter = new SuperPrinter();
		final VisitedNodesManager manager = new VisitedNodesManager();
		node1.setMessage(message);
		new BFSMessageSending().sendMessage(node1, manager);
//		final GraphMessageSendingManager messageSendingManager = new GraphMessageSendingManager();
//
//		// Sequential data sending from the root node
//		LOGGER.info("\n**** SENDING A MESSAGE SEQUENTIALLY FROM THE ROOT NODE ****");
//		messageSendingManager
//				.setMessageSendingMethod(new SequentialMessageSendingFromTheRootNode());
//		messageSendingManager.fullMessageSendingProcess(message, node1,
//				superPrinter, nodes);
//		messageSendingManager.resetDatas(nodes);
//
//		// Sequential data sending from any node
//		LOGGER.info("\n**** SENDING A MESSAGE SEQUENTIALLY FROM ANY NODE ****");
//		messageSendingManager
//				.setMessageSendingMethod(new SequentialMessageSendingFromAnyNode());
//		messageSendingManager.fullMessageSendingProcess(message, node5,
//				superPrinter, nodes);
//		messageSendingManager.resetDatas(nodes);
//
//		// Concurrent data sending from the root node
//		LOGGER.info("\n**** SENDING A MESSAGE SIMULTANEOUSLY FROM THE ROOT NODE ****");
//		messageSendingManager
//				.setMessageSendingMethod(new ConcurrentMessageSendingFromTheRootNode());
//		messageSendingManager.fullMessageSendingProcess(message, node1,
//				superPrinter, nodes);
//		messageSendingManager.resetDatas(nodes);
//
//		// Data sending from any node
//		LOGGER.info("\n**** SENDING A MESSAGE SIMULTANEOUSLY FROM ANY NODE ****\n");
//		messageSendingManager
//				.setMessageSendingMethod(new ConcurrentMessageSendingFromAnyNode());
//		messageSendingManager.fullMessageSendingProcess(message, node5,
//				superPrinter, nodes);
//		messageSendingManager.resetDatas(nodes);
	}

}
