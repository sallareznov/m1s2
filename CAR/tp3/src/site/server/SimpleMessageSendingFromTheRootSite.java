package site.server;

public class SimpleMessageSendingFromTheRootSite implements
		MessageSendingMethod {

	@Override
	public void sendMessage(final Site sender,
			VisitedSitesManager visitedSitesManager,
			final MessageSendingManagerImpl messageSendingManager)
			throws InterruptedException {
		final Site[] sons = sender.getFils();
		if (sons == null) {
			return;
		}
		for (final Site aSon : sons) {
			messageSendingManager.spreadMessage(sender, aSon);
		}
	}

}
