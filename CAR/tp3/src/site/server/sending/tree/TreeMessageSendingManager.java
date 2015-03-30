package site.server.sending.tree;

import java.rmi.RemoteException;

import site.server.SuperPrinter;
import site.server.VisitedNodesManager;
import site.server.bean.tree.TreeNode;

public class TreeMessageSendingManager {

	private TreeMessageSendingMethod sendingMethod;
	private VisitedNodesManager visitedNodes;
	private SuperPrinter tracePrinter;

	public TreeMessageSendingManager() {
		sendingMethod = null;
		visitedNodes = new VisitedNodesManager();
		tracePrinter = new SuperPrinter();
	}

	public void setMessageSendingMethod(
			TreeMessageSendingMethod dataSendingMethod) {
		this.sendingMethod = dataSendingMethod;
	}

	public void sendMessage(TreeNode sender) throws InterruptedException,
			RemoteException {
		visitedNodes.reset();
		visitedNodes.markAsVisited(sender);
		sendingMethod.sendMessage(sender, visitedNodes, this);
	}

	public void spreadMessage(TreeNode sender, TreeNode receiver)
			throws RemoteException {
		tracePrinter.printMessageReceived(sender, receiver);
		receiver.setMessage(sender.getMessage());
		visitedNodes.markAsVisited(receiver);
		if (receiver.getSons() == null) {
			return;
		}
		for (final TreeNode aSon : receiver.getSons()) {
			if (!visitedNodes.isVisited(aSon) && !aSon.equals(sender)) {
				tracePrinter.printMessageBeingSpreaded(receiver, aSon);
				spreadMessage(receiver, aSon );
			}
		}
	}

	public void fullMessageSendingProcess(String message, TreeNode sender,
			SuperPrinter printer, TreeNode... nodes) throws RemoteException,
			InterruptedException {
		sender.setMessage(message);
		printer.printBeforeSending();
		printer.printNodes(nodes);
		printer.printDuringSending();
		sendMessage(sender);
		printer.printAfterSending();
		printer.printNodes(nodes);
	}
	
	public void resetDatas(TreeNode... nodes) throws RemoteException {
		for (final TreeNode node : nodes) {
			node.resetMessage();
		}
	}

}
