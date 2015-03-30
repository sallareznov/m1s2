package site.server.sending.tree;

import java.rmi.RemoteException;

import site.server.VisitedNodesManager;
import site.server.bean.tree.TreeNode;

/**
 * Sending method of a message by a tree-node
 */
public interface TreeMessageSendingMethod {

	/**
	 * sends the message
	 * 
	 * @param sender
	 *            the sender of the message
	 * @param visitedNodesManager
	 *            the manager of the visited nodes
	 * @throws RemoteException
	 * @throws InterruptedException
	 */
	void sendMessage(TreeNode sender, VisitedNodesManager visitedNodesManager,
			TreeMessageSendingManager messageSendingManager)
			throws RemoteException, InterruptedException;

}
