package site.server.sending.tree.concurrent;

import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import site.server.VisitedNodesManager;
import site.server.bean.tree.TreeNode;
import site.server.sending.tree.TreeMessageSendingManager;
import site.server.sending.tree.TreeMessageSendingMethod;
import site.shared.logger.LoggerFactory;

public class ConcurrentMessageSendingFromTheRootNode implements
		TreeMessageSendingMethod {
	
	private static final Logger LOGGER = LoggerFactory.create(ConcurrentMessageSendingFromTheRootNode.class);

	@Override
	public void sendMessage(final TreeNode sender,
			VisitedNodesManager visitedSitesManager,
			final TreeMessageSendingManager messageSendingManager)
			throws RemoteException, InterruptedException {
		final TreeNode[] sons = sender.getSons();
		if (sons == null) {
			return;
		}
		final List<Thread> threads = new LinkedList<Thread>();
		for (final TreeNode aSon : sons) {
			final Thread thread = new Thread(new Runnable() {

				@Override
				public void run() {
					try {
						messageSendingManager.spreadMessage(sender, aSon);
					} catch (RemoteException e) {
						LOGGER.throwing("ConcurrentMessageSendingFromTheRootSite", "sendMessage()", e);
					}
				}

			});
			thread.start();
			threads.add(thread);
		}
		for (Thread thread : threads) {
			thread.join();
		}
	}

}
