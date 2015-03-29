package site.client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import site.server.VisitedNodesManager;
import site.server.bean.graph.GraphNode;
import site.server.sending.graph.BFSMessageSending;

public class GraphClientHandler implements ClientHandler {

	@Override
	public void execute(String serverAddress, String message) throws RemoteException,
			InterruptedException, NotBoundException {
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

		node1.addNeighbor(node2);
		node1.addNeighbor(node5);
		node2.addNeighbor(node3);
		node2.addNeighbor(node4);
		node5.addNeighbor(node6);
		node5.addNeighbor(node3);
		node4.addNeighbor(node6);

		final VisitedNodesManager manager = new VisitedNodesManager();
		node1.setMessage(message);
		new BFSMessageSending().sendMessage(node1, manager);
	}

}
