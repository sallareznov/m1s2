package site.server.sending;

import java.util.LinkedList;
import java.util.Queue;

import site.server.GraphSite;
import site.server.VisitedSitesManager;

public class BFSMessageSending {

	public void sendMessage(GraphSite sender,
			VisitedSitesManager visitedSitesManager)
			throws InterruptedException {
		final Queue<GraphSite> queuedSites = new LinkedList<GraphSite>();
		queuedSites.add(sender);
		while (!queuedSites.isEmpty()) {
			final GraphSite topSite = queuedSites.poll();
			final String messageToSend = sender.getMessage();
			visitedSitesManager.markAsVisited(topSite);
			topSite.setMessage(messageToSend);
			for (final GraphSite neighbor : topSite.getNeighbors()) {
				if (!visitedSitesManager.isVisited(neighbor))
					queuedSites.add(neighbor);
			}
		}
	}

}
