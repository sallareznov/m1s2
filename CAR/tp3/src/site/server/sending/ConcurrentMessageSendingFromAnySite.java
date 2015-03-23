package site.server.sending;

import site.server.TreeSite;
import site.server.VisitedSitesManager;

public class ConcurrentMessageSendingFromAnySite implements MessageSendingMethod {

	@Override
	public void sendMessage(final TreeSite expediteur,
			VisitedSitesManager visitedSitesManager,
			final MessageSendingManagerImpl messageSendingManager)
			throws InterruptedException {
		visitedSitesManager.markAsVisited(expediteur);
		final TreeSite pere = expediteur.getPere();
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
		final TreeSite[] fils = expediteur.getFils();
		if (fils != null) {
			for (final TreeSite unFils : fils) {
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
