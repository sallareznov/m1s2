package site.server.sending.tree.concurrent;

import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.List;

import site.server.VisitedSitesManager;
import site.server.bean.tree.TreeNode;
import site.server.sending.tree.TreeMessageSendingManagerImpl;
import site.server.sending.tree.TreeMessageSendingMethod;

public class ConcurrentMessageSendingFromTheRootSite implements
		TreeMessageSendingMethod {

	@Override
	public void sendMessage(final TreeNode sender,
			VisitedSitesManager visitedSitesManager,
			final TreeMessageSendingManagerImpl messageSendingManager)
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
						e.printStackTrace();
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
