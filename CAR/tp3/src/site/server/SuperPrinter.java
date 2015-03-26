package site.server;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.logging.Logger;

import site.server.bean.Node;
import site.server.bean.tree.TreeNode;
import site.shared.LoggerFactory;

public class SuperPrinter implements Serializable {
	
	private static final long serialVersionUID = 378869235800086735L;
	private static final Logger LOGGER = LoggerFactory.create(SuperPrinter.class);

	public SuperPrinter() {
	}
	
	public void printMessageReceived(TreeNode sender, TreeNode receiver) throws RemoteException {
		LOGGER.info("<--- [" + receiver.printMe() + "] : " + "Just a received a message coming from " + sender.printMe());
	}
	
	public void printMessageBeingSpreaded(TreeNode sender, TreeNode receiver) throws RemoteException {
		LOGGER.info("---> [" + sender.printMe() + "] : " + "Spreading message towards " + receiver.printMe());
	}
	
	public void printSite(Node site) throws RemoteException {
		final StringBuilder builder = new StringBuilder();
		builder.append("Site ");
		builder.append(site.getId());
		builder.append(" : ");
		builder.append("\n");
		builder.append("message = ");
		builder.append("".equals(site.getMessage()) ? "No message" : site.getMessage());
		LOGGER.info(builder.toString());
	}
	
	public void printSites(Node ... sites) throws RemoteException {
		for (final Node site : sites) {
			printSite(site);
		}
	}
	
	public void printBeforeSending() {
		LOGGER.info("------- Before sending -------");
	}
	
	public void printDuringSending() {
		LOGGER.info("------- During sending -------");
	}
	
	public void printAfterSending() {
		LOGGER.info("------- After sending -------");
	}
	
}
