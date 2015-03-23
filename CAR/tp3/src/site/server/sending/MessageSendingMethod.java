package site.server.sending;

import site.server.TreeSite;
import site.server.VisitedSitesManager;

public interface MessageSendingMethod {

	void sendMessage(TreeSite sender, VisitedSitesManager visitedSitesManager,
			MessageSendingManagerImpl messageSendingManager)
			throws InterruptedException;

}
