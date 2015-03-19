package site.server;

public class SimpleMessageSendingFromAnySite implements MessageSendingMethod {

	@Override
	public void sendMessage(final Site expediteur,
			VisitedSitesManager visitedSitesManager,
			final MessageSendingManagerImpl messageSendingManager)
			throws InterruptedException {
		visitedSitesManager.markAsVisited(expediteur);
		final Site pere = expediteur.getPere();
		if (pere != null) {
			messageSendingManager.spreadMessage(expediteur, pere);
		}
		final Site[] fils = expediteur.getFils();
		if (fils != null) {
			for (final Site unFils : fils) {
				messageSendingManager.spreadMessage(expediteur, unFils);
			}
		}
	}

}
