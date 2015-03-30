package site.server;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.logging.Logger;

import site.server.bean.Node;
import site.shared.logger.LoggerFactory;

public class SuperPrinter implements Serializable {
	
	private static final long serialVersionUID = 378869235800086735L;
	private static final Logger LOGGER = LoggerFactory.create(SuperPrinter.class);

	public SuperPrinter() {
	}
	
	public void printMessageReceived(Node sender, Node receiver) throws RemoteException {
		LOGGER.info("<--- [" + receiver.printMe() + "] : " + "Just a received a message coming from " + sender.printMe());
	}
	
	public void printMessageBeingSpreaded(Node sender, Node receiver) throws RemoteException {
		LOGGER.info("---> [" + sender.printMe() + "] : " + "Spreading message towards " + receiver.printMe());
	}
	
	public void printNode(Node node) throws RemoteException {
		final StringBuilder builder = new StringBuilder();
		builder.append("Site ");
		builder.append(node.getId());
		builder.append(" : ");
		builder.append("\n");
		builder.append("message = ");
		builder.append("".equals(node.getMessage()) ? "No message" : node.getMessage());
		LOGGER.info(builder.toString());
	}
	
	public void printNodes(Node ... sites) throws RemoteException {
		for (final Node site : sites) {
			printNode(site);
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
