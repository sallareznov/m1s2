package site.client;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.util.logging.Logger;

import site.server.TreeSite;
import site.server.SiteFactory;
import site.server.SuperPrinter;
import site.server.sending.ConcurrentMessageSendingFromAnySite;
import site.server.sending.ConcurrentMessageSendingFromTheRootSite;
import site.server.sending.MessageSendingManagerImpl;
import site.server.sending.SimpleMessageSendingFromAnySite;
import site.shared.LoggerFactory;

public class Client {

	private static final Logger LOGGER = LoggerFactory.create(Client.class);

	private Client() {
	}

	public static void main(String[] args) throws UnknownHostException,
			MalformedURLException, RemoteException, NotBoundException,
			InterruptedException {
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new RMISecurityManager());
		}
		final String key = "rmi://"
				+ InetAddress.getLocalHost().getHostAddress() + "/SiteFactory";

		// Obtention of the factory at the server
		final SiteFactory siteFactory = (SiteFactory) Naming.lookup(key);

		// Construction of the tree-like structure
		final TreeSite site1 = siteFactory.create(1);
		final TreeSite site2 = siteFactory.create(2);
		final TreeSite site3 = siteFactory.create(3);
		final TreeSite site4 = siteFactory.create(4);
		final TreeSite site5 = siteFactory.create(5);
		final TreeSite site6 = siteFactory.create(6);
		site1.setFils(site2, site5);
		site2.setFils(site3, site4);
		site5.setFils(site6);

		// Instanciation of the data to send and the site printer
		final String message = "RMI rocks !";
		final SuperPrinter superPrinter = new SuperPrinter();
		final MessageSendingManagerImpl messageSendingManager = new MessageSendingManagerImpl();

		LOGGER.info("\n**** SENDING A MESSAGE SEQUENTIALLY FROM THE ROOT SITE ****");
		messageSendingManager
				.setMessageSendingMethod(new SimpleMessageSendingFromAnySite());
		site5.setMessage(message);
		superPrinter.printBeforeSending();
		superPrinter.printSites(site1, site2, site3, site4, site5, site6);
		superPrinter.printDuringSending();
		messageSendingManager.sendMessage(site5);
		superPrinter.printAfterSending();
		superPrinter.printSites(site1, site2, site3, site4, site5, site6);

		// Data resetting
		site1.reset();
		site2.reset();
		site3.reset();
		site4.reset();
		site5.reset();
		site6.reset();

		// Data sending from the root site
		LOGGER.info("\n**** SENDING A MESSAGE SIMULTANEOUSLY FROM THE ROOT SITE ****");
		messageSendingManager
				.setMessageSendingMethod(new ConcurrentMessageSendingFromTheRootSite());
		site1.setMessage(message);
		superPrinter.printBeforeSending();
		superPrinter.printSites(site1, site2, site3, site4, site5, site6);
		superPrinter.printDuringSending();
		messageSendingManager.sendMessage(site1);
		superPrinter.printAfterSending();
		superPrinter.printSites(site1, site2, site3, site4, site5, site6);

		// Data resetting
		site1.reset();
		site2.reset();
		site3.reset();
		site4.reset();
		site5.reset();
		site6.reset();

		// Data sending from any site
		LOGGER.info("\n**** SENDING A MESSAGE SIMULTANEOUSLY FROM ANY SITE ****\n");
		messageSendingManager
				.setMessageSendingMethod(new ConcurrentMessageSendingFromAnySite());
		site5.setMessage(message);
		superPrinter.printBeforeSending();
		superPrinter.printSites(site1, site2, site3, site4, site5, site6);
		superPrinter.printDuringSending();
		messageSendingManager.sendMessage(site5);
		superPrinter.printAfterSending();
		superPrinter.printSites(site1, site2, site3, site4, site5, site6);
	}

}
