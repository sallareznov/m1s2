package site.server.sending;

import java.util.LinkedList;
import java.util.List;

import site.server.TreeSite;
import site.server.VisitedSitesManager;


public class ConcurrentMessageSendingFromTheRootSite implements MessageSendingMethod {
	
	@Override
	public void sendMessage(final TreeSite sender, VisitedSitesManager visitedSitesManager, final MessageSendingManagerImpl messageSendingManager) throws InterruptedException {
		final TreeSite[] sons = sender.getFils();
		if (sons == null) {
			return;
		}
		final List<Thread> threads = new LinkedList<Thread>();
		for (final TreeSite aSon : sons) {
			final Thread thread = new Thread(new Runnable() {

				@Override
				public void run() {
					messageSendingManager.spreadMessage(sender, aSon);
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
