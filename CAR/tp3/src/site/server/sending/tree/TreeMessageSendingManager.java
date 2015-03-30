package site.server.sending.tree;

import java.rmi.RemoteException;

import site.server.SuperPrinter;
import site.server.VisitedNodesManager;
import site.server.bean.tree.TreeNode;

public class TreeMessageSendingManager {

	private TreeMessageSendingMethod sendingMethod;
	private VisitedNodesManager visitedSites;
	private SuperPrinter tracePrinter;

	public TreeMessageSendingManager() {
		sendingMethod = null;
		visitedSites = new VisitedNodesManager();
		tracePrinter = new SuperPrinter();
	}

	public void setMessageSendingMethod(
			TreeMessageSendingMethod dataSendingMethod) {
		this.sendingMethod = dataSendingMethod;
	}

	public void sendMessage(TreeNode sender) throws InterruptedException,
			RemoteException {
		visitedSites.reset();
		visitedSites.markAsVisited(sender);
		sendingMethod.sendMessage(sender, visitedSites, this);
	}

	public void spreadMessage(TreeNode sender, TreeNode receiver)
			throws RemoteException {
		tracePrinter.printMessageReceived(sender, receiver);
		receiver.setMessage(sender.getMessage());
		visitedSites.markAsVisited(receiver);
		if (receiver.getSons() == null) {
			return;
		}
		for (final TreeNode unFils : receiver.getSons()) {
			if (!visitedSites.isVisited(unFils) && !unFils.equals(sender)) {
				tracePrinter.printMessageBeingSpreaded(receiver, unFils);
				spreadMessage(receiver, unFils);
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
