package site.server.sending.tree.sequential;

import java.rmi.RemoteException;

import site.server.VisitedNodesManager;
import site.server.bean.tree.TreeNode;
import site.server.sending.tree.TreeMessageSendingManager;
import site.server.sending.tree.TreeMessageSendingMethod;

public class SequentialMessageSendingFromTheRootNode implements
		TreeMessageSendingMethod {

	@Override
	public void sendMessage(final TreeNode sender,
			VisitedNodesManager visitedSitesManager,
			final TreeMessageSendingManager messageSendingManager)
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
