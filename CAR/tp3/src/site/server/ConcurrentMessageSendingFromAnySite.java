package site.server;

public class ConcurrentMessageSendingFromAnySite implements MessageSendingMethod {

	@Override
	public void sendMessage(final Site expediteur,
			VisitedSitesManager visitedSitesManager,
			final MessageSendingManagerImpl messageSendingManager)
			throws InterruptedException {
		visitedSitesManager.markAsVisited(expediteur);
		final Site pere = expediteur.getPere();
		if (pere != null) {
			final Thread thread = new Thread(new Runnable() {

				@Override
				public void run() {
					messageSendingManager.spreadMessage(expediteur, pere);
				}

			});
			thread.start();
			thread.join();
		}
		final Site[] fils = expediteur.getFils();
		if (fils != null) {
			for (final Site unFils : fils) {
				final Thread thread = new Thread(new Runnable() {

					@Override
					public void run() {
						messageSendingManager.spreadMessage(expediteur, unFils);
					}

				});
				thread.start();
				thread.join();
			}
		}
	}

}
