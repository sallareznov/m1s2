package site.client;

import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.logging.Logger;

import site.server.bean.tree.TreeNode;
import site.server.sending.tree.TreeMessageSendingManagerImpl;
import site.server.sending.tree.concurrent.ConcurrentMessageSendingFromAnySite;
import site.server.sending.tree.sequential.SequentialMessageSendingFromAnySite;
import site.server.sending.tree.sequential.SequentialMessageSendingFromTheRootSite;
import site.shared.LoggerFactory;

public class Client {

	private static final Logger LOGGER = LoggerFactory.create(Client.class);

	public static void main(String[] args) throws AccessException,
			RemoteException, NotBoundException, InterruptedException {
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new RMISecurityManager());
		}
		final TreeNode node1 = (TreeNode) LocateRegistry.getRegistry(1099)
				.lookup("node1");
		final TreeNode node2 = (TreeNode) LocateRegistry.getRegistry(1099)
				.lookup("node2");
		final TreeNode node3 = (TreeNode) LocateRegistry.getRegistry(1099)
				.lookup("node3");
		final TreeNode node4 = (TreeNode) LocateRegistry.getRegistry(1099)
				.lookup("node4");
		final TreeNode node5 = (TreeNode) LocateRegistry.getRegistry(1099)
				.lookup("node5");
		final TreeNode node6 = (TreeNode) LocateRegistry.getRegistry(1099)
				.lookup("node6");

		node1.setSons(node2, node5);
		node2.setSons(node3, node4);
		node5.setSons(node6);

		// Instanciation of the data to send and the node printer
		final String message = "RMI rocks !";
		final SuperPrinter superPrinter = new SuperPrinter();
		final TreeMessageSendingManagerImpl messageSendingManager = new TreeMessageSendingManagerImpl();

		LOGGER.info("\n**** SENDING A MESSAGE SEQUENTIALLY FROM THE ROOT SITE ****");
		messageSendingManager
				.setMessageSendingMethod(new SequentialMessageSendingFromTheRootSite());
		node1.setMessage(message);
		superPrinter.printBeforeSending();
		superPrinter.printSites(node1, node2, node3, node4, node5, node6);
		superPrinter.printDuringSending();
		messageSendingManager.sendMessage(node1);
		superPrinter.printAfterSending();
		superPrinter.printSites(node1, node2, node3, node4, node5, node6);

		// Data resetting
		node1.resetMessage();
		node2.resetMessage();
		node3.resetMessage();
		node4.resetMessage();
		node5.resetMessage();
		node6.resetMessage();

		// Data sending from the root node
		LOGGER.info("\n**** SENDING A MESSAGE SEQUENTIALLY FROM ANY SITE ****");
		messageSendingManager
				.setMessageSendingMethod(new SequentialMessageSendingFromAnySite());
		node5.setMessage(message);
		superPrinter.printBeforeSending();
		superPrinter.printSites(node1, node2, node3, node4, node5, node6);
		superPrinter.printDuringSending();
		messageSendingManager.sendMessage(node5);
		superPrinter.printAfterSending();
		superPrinter.printSites(node1, node2, node3, node4, node5, node6);

		// Data resetting
		node1.resetMessage();
		node2.resetMessage();
		node3.resetMessage();
		node4.resetMessage();
		node5.resetMessage();
		node6.resetMessage();

		// Data sending from the root node
		LOGGER.info("\n**** SENDING A MESSAGE SIMULTANEOUSLY FROM THE ROOT SITE ****");
		messageSendingManager
				.setMessageSendingMethod(new ConcurrentMessageSendingFromAnySite());
		node1.setMessage(message);
		superPrinter.printBeforeSending();
		superPrinter.printSites(node1, node2, node3, node4, node5, node6);
		superPrinter.printDuringSending();
		messageSendingManager.sendMessage(node1);
		superPrinter.printAfterSending();
		superPrinter.printSites(node1, node2, node3, node4, node5, node6);

		// Data resetting
		node1.resetMessage();
		node2.resetMessage();
		node3.resetMessage();
		node4.resetMessage();
		node5.resetMessage();
		node6.resetMessage();

		// Data sending from any node
		LOGGER.info("\n**** SENDING A MESSAGE SIMULTANEOUSLY FROM ANY SITE ****\n");
		messageSendingManager
				.setMessageSendingMethod(new ConcurrentMessageSendingFromAnySite());
		node5.setMessage(message);
		superPrinter.printBeforeSending();
		superPrinter.printSites(node1, node2, node3, node4, node5, node6);
		superPrinter.printDuringSending();
		messageSendingManager.sendMessage(node5);
		superPrinter.printAfterSending();
		superPrinter.printSites(node1, node2, node3, node4, node5, node6);

	}

}
