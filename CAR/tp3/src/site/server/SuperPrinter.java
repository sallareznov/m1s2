package site.server;

import java.io.Serializable;
import java.util.logging.Logger;

import site.shared.LoggerFactory;

public class SuperPrinter implements Serializable {
	
	private static final long serialVersionUID = 378869235800086735L;
	private static final Logger LOGGER = LoggerFactory.create(SuperPrinter.class);

	public SuperPrinter() {
	}
	
	public void printMessageReceived(TreeSite sender, TreeSite receiver) {
		LOGGER.info("<--- [" + receiver + "] : " + "Just a received a message coming from " + sender);
	}
	
	public void printMessageBeingSpreaded(TreeSite sender, TreeSite receiver) {
		LOGGER.info("---> [" + sender + "] : " + "Spreading message towards " + receiver);
	}
	
	public void printSite(TreeSite site) {
		final StringBuilder builder = new StringBuilder();
		builder.append("Site ");
		builder.append(site.getId());
		builder.append(" : ");
		builder.append("\n");
		builder.append("message = ");
		builder.append("".equals(site.getMessage()) ? "No message" : site.getMessage());
		LOGGER.info(builder.toString());
	}
	
	public void printSites(TreeSite ... sites) {
		for (final TreeSite site : sites) {
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
