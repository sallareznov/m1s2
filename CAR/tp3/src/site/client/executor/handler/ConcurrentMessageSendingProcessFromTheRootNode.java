package site.client.executor.handler;

import java.rmi.RemoteException;
import java.util.logging.Logger;

import site.server.SuperPrinter;
import site.server.bean.tree.TreeNode;
import site.server.sending.tree.TreeMessageSendingManager;
import site.server.sending.tree.concurrent.ConcurrentMessageSendingFromTheRootNode;
import site.shared.logger.LoggerFactory;

public class ConcurrentMessageSendingProcessFromTheRootNode extends
		AbstractMessageSendingProcess {

	private static final Logger LOGGER = LoggerFactory
			.create(ConcurrentMessageSendingProcessFromTheRootNode.class);

	public ConcurrentMessageSendingProcessFromTheRootNode() {
		super("-cr", new ConcurrentMessageSendingFromTheRootNode());
	}

	@Override
	public void logMessageSendingMethod() {
		LOGGER.info("**** SENDING A MESSAGE SIMULTANEOUSLY FROM THE ROOT NODE ****");
	}

	@Override
	public void fullMessageSendingProcess(String[] args, TreeNode[] nodesArray,
			TreeMessageSendingManager messageSendingManager,
			SuperPrinter superPrinter) throws RemoteException,
			InterruptedException {
		messageSendingManager.fullMessageSendingProcess(args[2], nodesArray[0],
				superPrinter, nodesArray);
	}

}
