package site.server.sending.tree.sequential;

import java.rmi.RemoteException;

import site.server.VisitedSitesManager;
import site.server.bean.tree.TreeNode;
import site.server.sending.tree.TreeMessageSendingManagerImpl;
import site.server.sending.tree.TreeMessageSendingMethod;

public class SequentialMessageSendingFromTheRootSite implements
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
		for (final TreeNode aSon : sons) {
			messageSendingManager.spreadMessage(sender, aSon);
		}
	}

}
