package site.server.sending.tree;

import java.rmi.RemoteException;

import site.client.SuperPrinter;
import site.server.VisitedSitesManager;
import site.server.bean.tree.TreeNode;

public class TreeMessageSendingManagerImpl {
	
	private TreeMessageSendingMethod sendingMethod;
	private VisitedSitesManager visitedSites;
	private SuperPrinter tracePrinter;
	
	public TreeMessageSendingManagerImpl() {
		sendingMethod = null;
		visitedSites = new VisitedSitesManager();
		tracePrinter = new SuperPrinter();
	}
	
	public void setMessageSendingMethod(TreeMessageSendingMethod dataSendingMethod) {
		this.sendingMethod = dataSendingMethod;
	}
	
	public void sendMessage(TreeNode sender) throws InterruptedException, RemoteException {
		visitedSites.reset();
		visitedSites.markAsVisited(sender);
		sendingMethod.sendMessage(sender, visitedSites, this);
	}
	
	public void spreadMessage(TreeNode sender, TreeNode receiver) throws RemoteException {
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
	
}
