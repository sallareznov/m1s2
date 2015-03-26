package site.server.sending.tree.concurrent;

import java.rmi.RemoteException;
import java.util.logging.Logger;

import site.server.VisitedNodesManager;
import site.server.bean.tree.TreeNode;
import site.server.sending.tree.TreeMessageSendingManager;
import site.server.sending.tree.TreeMessageSendingMethod;
import site.shared.LoggerFactory;

public class ConcurrentMessageSendingFromAnyNode implements TreeMessageSendingMethod {
	
	private static final Logger LOGGER = LoggerFactory.create(ConcurrentMessageSendingFromAnyNode.class);

	@Override
	public void sendMessage(final TreeNode expediteur,
			VisitedNodesManager visitedSitesManager,
			final TreeMessageSendingManager messageSendingManager)
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
						LOGGER.throwing("ConcurrentMessageSendingFromAnySite", "sendMessage()", e);
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
							LOGGER.throwing("ConcurrentMessageSendingFromAnySite", "sendMessage()", e);
						}
					}

				});
				thread.start();
				thread.join();
			}
		}
	}

}
