package site.server.sending.graph;

import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.Queue;

import site.server.VisitedNodesManager;
import site.server.bean.graph.GraphNode;

public class BFSMessageSending {

	public void sendMessage(GraphNode sender,
			VisitedNodesManager visitedSitesManager)
			throws InterruptedException, RemoteException {
		final Queue<GraphNode> queuedSites = new LinkedList<GraphNode>();
		queuedSites.add(sender);
		while (!queuedSites.isEmpty()) {
			final GraphNode topSite = queuedSites.poll();
			final String messageToSend = sender.getMessage();
			visitedSitesManager.markAsVisited(topSite);
			topSite.setMessage(messageToSend);
			System.out.println(topSite.printMe());
			for (final GraphNode neighbor : topSite.getNeighbors()) {
				if (!visitedSitesManager.isVisited(neighbor)) {
					queuedSites.add(neighbor);
					visitedSitesManager.markAsVisited(neighbor);
				}
			}
		}
	}

}
