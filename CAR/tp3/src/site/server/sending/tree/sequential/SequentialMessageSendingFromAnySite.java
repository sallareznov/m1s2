package site.server.sending.tree.sequential;

import java.rmi.RemoteException;

import site.server.VisitedSitesManager;
import site.server.bean.tree.TreeNode;
import site.server.sending.tree.TreeMessageSendingManagerImpl;
import site.server.sending.tree.TreeMessageSendingMethod;

public class SequentialMessageSendingFromAnySite implements TreeMessageSendingMethod {

	@Override
	public void sendMessage(final TreeNode expediteur,
			VisitedSitesManager visitedSitesManager,
			final TreeMessageSendingManagerImpl messageSendingManager)
			throws RemoteException, InterruptedException {
		visitedSitesManager.markAsVisited(expediteur);
		final TreeNode pere = expediteur.getFather();
		if (pere != null) {
			messageSendingManager.spreadMessage(expediteur, pere);
		}
		final TreeNode[] fils = expediteur.getSons();
		if (fils != null) {
			for (final TreeNode unFils : fils) {
				messageSendingManager.spreadMessage(expediteur, unFils);
			}
		}
	}

}
