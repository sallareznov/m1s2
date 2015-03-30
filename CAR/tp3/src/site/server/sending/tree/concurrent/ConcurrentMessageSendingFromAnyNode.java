package site.server.sending.tree.concurrent;

import java.rmi.RemoteException;
import java.util.logging.Logger;

import site.server.VisitedNodesManager;
import site.server.bean.tree.TreeNode;
import site.server.sending.tree.TreeMessageSendingManager;
import site.server.sending.tree.TreeMessageSendingMethod;
import site.shared.logger.LoggerFactory;

public class ConcurrentMessageSendingFromAnyNode implements TreeMessageSendingMethod {
	
	private static final Logger LOGGER = LoggerFactory.create(ConcurrentMessageSendingFromAnyNode.class);

	@Override
	public void sendMessage(final TreeNode expediteur,
			VisitedNodesManager visitedNodesManager,
			final TreeMessageSendingManager messageSendingManager)
			throws RemoteException, InterruptedException {
		visitedNodesManager.markAsVisited(expediteur);
		final TreeNode pere = expediteur.getFather();
		if (pere != null) {
			final Thread thread = new Thread(new Runnable() {

				@Override
				public void run() {
					try {
						messageSendingManager.spreadMessage(expediteur, pere);
					} catch (RemoteException e) {
						LOGGER.throwing(getClass().getName(), "sendMessage()", e);
					}
				}

			});
			thread.start();
			thread.join();
		}
		final TreeNode[] sons = expediteur.getSons();
		if (sons != null) {
			for (final TreeNode aSon : sons) {
				final Thread thread = new Thread(new Runnable() {

					@Override
					public void run() {
						try {
							messageSendingManager.spreadMessage(expediteur, aSon);
						} catch (RemoteException e) {
							LOGGER.throwing(getClass().getName(), "sendMessage()", e);
						}
					}

				});
				thread.start();
				thread.join();
			}
		}
	}

}
