package site.server.sending;

import site.server.TreeSite;
import site.server.SuperPrinter;
import site.server.VisitedSitesManager;

public class MessageSendingManagerImpl {
	
	private MessageSendingMethod sendingMethod;
	private VisitedSitesManager visitedSites;
	private SuperPrinter tracePrinter;
	
	public MessageSendingManagerImpl() {
		sendingMethod = null;
		visitedSites = new VisitedSitesManager();
		tracePrinter = new SuperPrinter();
	}
	
	public void setMessageSendingMethod(MessageSendingMethod dataSendingMethod) {
		this.sendingMethod = dataSendingMethod;
	}
	
	public void sendMessage(TreeSite sender) throws InterruptedException {
		visitedSites.reset();
		visitedSites.markAsVisited(sender);
		sendingMethod.sendMessage(sender, visitedSites, this);
	}
	
	public void spreadMessage(TreeSite sender, TreeSite receiver) {
		tracePrinter.printMessageReceived(sender, receiver);
		receiver.setMessage(sender.getMessage());
		visitedSites.markAsVisited(receiver);
		if (receiver.getFils() == null) {
			return;
		}
		for (final TreeSite unFils : receiver.getFils()) {
			if (!visitedSites.isVisited(unFils) && !unFils.equals(sender)) {
				tracePrinter.printMessageBeingSpreaded(receiver, unFils);
				spreadMessage(receiver, unFils);
			}
		}
	}
	
}
