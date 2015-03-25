package site.server.sending.tree.concurrent;

import java.rmi.RemoteException;

import site.server.VisitedSitesManager;
import site.server.bean.tree.TreeNode;
import site.server.sending.tree.TreeMessageSendingManagerImpl;
import site.server.sending.tree.TreeMessageSendingMethod;

public class ConcurrentMessageSendingFromAnySite implements TreeMessageSendingMethod {

	@Override
	public void sendMessage(final TreeNode expediteur,
			VisitedSitesManager visitedSitesManager,
			final TreeMessageSendingManagerImpl messageSendingManager)
			throws RemoteException, InterruptedException {
		visitedSitesManager.markAsVisited(expediteur);
		final TreeNode pere = expediteur.getFather();
		if (pere != null) {
			final Thread thread = new Thread(new Runnable() {

				@Override
				public void run() {
					try {
						messageSendingManager.spreadMessage(expediteur, pere);
					} catch (RemoteException e) {
						e.printStackTrace();
					}
				}

			});
			thread.start();
			thread.join();
		}
		final TreeNode[] fils = expediteur.getSons();
		if (fils != null) {
			for (final TreeNode unFils : fils) {
				final Thread thread = new Thread(new Runnable() {

					@Override
					public void run() {
						try {
							messageSendingManager.spreadMessage(expediteur, unFils);
						} catch (RemoteException e) {
							e.printStackTrace();
						}
					}

				});
				thread.start();
				thread.join();
			}
		}
	}

}
