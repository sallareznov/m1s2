package site.server.sending;

import site.server.TreeSite;
import site.server.VisitedSitesManager;

public class SimpleMessageSendingFromAnySite implements MessageSendingMethod {

	@Override
	public void sendMessage(final TreeSite expediteur,
			VisitedSitesManager visitedSitesManager,
			final MessageSendingManagerImpl messageSendingManager)
			throws InterruptedException {
		visitedSitesManager.markAsVisited(expediteur);
		final TreeSite pere = expediteur.getPere();
		if (pere != null) {
			messageSendingManager.spreadMessage(expediteur, pere);
		}
		final TreeSite[] fils = expediteur.getFils();
		if (fils != null) {
			for (final TreeSite unFils : fils) {
				messageSendingManager.spreadMessage(expediteur, unFils);
			}
		}
	}

}
