package site.server.sending;

import site.server.TreeSite;
import site.server.VisitedSitesManager;

public class SimpleMessageSendingFromTheRootSite implements
		MessageSendingMethod {

	@Override
	public void sendMessage(final TreeSite sender,
			VisitedSitesManager visitedSitesManager,
			final MessageSendingManagerImpl messageSendingManager)
			throws InterruptedException {
		final TreeSite[] sons = sender.getFils();
		if (sons == null) {
			return;
		}
		for (final TreeSite aSon : sons) {
			messageSendingManager.spreadMessage(sender, aSon);
		}
	}

}
