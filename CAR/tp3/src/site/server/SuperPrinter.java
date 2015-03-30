package site.server;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.logging.Logger;

import site.server.bean.Node;
import site.shared.logger.LoggerFactory;

/**
 * Prints everything associated to a node and its message's propagation
 */
public class SuperPrinter implements Serializable {
	
	private static final long serialVersionUID = 378869235800086735L;
	private static final Logger LOGGER = LoggerFactory.create(SuperPrinter.class);
	
	/**
	 * printing when a message is received by a node
	 * @param sender the sender of the message
	 * @param receiver the receiver of the message
	 * @throws RemoteException
	 */
	public void printMessageReceived(Node sender, Node receiver) throws RemoteException {
		LOGGER.info("<--- [" + receiver.printId() + "] : " + "Just a received a message coming from " + sender.printId());
	}
	
	/**
	 * printing when a message is being sended by a node
	 * @param spreader the spreader of the message
	 * @param receiver the receiver of message
	 * @throws RemoteException
	 */
	public void printMessageBeingSpreaded(Node spreader, Node receiver) throws RemoteException {
		LOGGER.info("---> [" + spreader.printId() + "] : " + "Spreading message towards " + receiver.printId());
	}
	
	/**
	 * prints nodes' informations
	 * @param nodes the nodes
	 * @throws RemoteException
	 */
	public void printNodes(Node ... nodes) throws RemoteException {
		for (final Node site : nodes) {
			LOGGER.info(site.getAllInfos());
		}
	}
	
	/**
	 * prints 'Before sending'
	 */
	public void printBeforeSending() {
		LOGGER.info("------- Before sending -------");
	}
	
	/**
	 * prints 'During sending'
	 */
	public void printDuringSending() {
		LOGGER.info("------- During sending -------");
	}
	
	/**
	 * prints 'After sending'
	 */
	public void printAfterSending() {
		LOGGER.info("------- After sending -------");
	}
	
}
