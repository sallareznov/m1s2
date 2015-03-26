package site.server.sending.tree;

import java.rmi.RemoteException;

import site.server.VisitedNodesManager;
import site.server.bean.tree.TreeNode;

public interface TreeMessageSendingMethod {

	void sendMessage(TreeNode sender, VisitedNodesManager visitedSitesManager,
			TreeMessageSendingManager messageSendingManager) throws RemoteException, InterruptedException;

}
