package site.server;

import java.util.LinkedList;
import java.util.List;


public class ConcurrentMessageSendingFromTheRootSite implements MessageSendingMethod {
	
	@Override
	public void sendMessage(final Site sender, VisitedSitesManager visitedSitesManager, final MessageSendingManagerImpl messageSendingManager) throws InterruptedException {
		final Site[] sons = sender.getFils();
		if (sons == null) {
			return;
		}
		final List<Thread> threads = new LinkedList<Thread>();
		for (final Site aSon : sons) {
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
