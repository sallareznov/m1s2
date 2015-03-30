package site.server.sending.graph;

import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Logger;

import site.server.VisitedNodesManager;
import site.server.bean.graph.GraphNode;
import site.shared.logger.LoggerFactory;

public class BFSMessageSending {
	
	private static final Logger LOGGER = LoggerFactory.create(BFSMessageSending.class);

	public void sendMessage(GraphNode sender,
			VisitedNodesManager visitedNodesManager)
			throws InterruptedException, RemoteException {
		final Queue<GraphNode> queuedNodes = new LinkedList<GraphNode>();
		queuedNodes.add(sender);
		while (!queuedNodes.isEmpty()) {
			final GraphNode topNode = queuedNodes.poll();
			final String messageToSend = sender.getMessage();
			visitedNodesManager.markAsVisited(topNode);
			topNode.setMessage(messageToSend);
			LOGGER.info(topNode.printId());
			for (final GraphNode neighbor : topNode.getNeighbors()) {
				if (!visitedNodesManager.isVisited(neighbor)) {
					queuedNodes.add(neighbor);
					visitedNodesManager.markAsVisited(neighbor);
				}
			}
		}
	}

}
