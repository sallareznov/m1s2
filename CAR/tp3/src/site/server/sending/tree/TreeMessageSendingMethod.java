package site.server.sending.tree;

import java.rmi.RemoteException;

import site.server.VisitedSitesManager;
import site.server.bean.tree.TreeNode;

public interface TreeMessageSendingMethod {

	void sendMessage(TreeNode sender, VisitedSitesManager visitedSitesManager,
			TreeMessageSendingManagerImpl messageSendingManager) throws RemoteException, InterruptedException;

}
