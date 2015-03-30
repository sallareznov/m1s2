package site.client.executor.handler;

import java.rmi.RemoteException;
import java.util.logging.Logger;

import site.server.SuperPrinter;
import site.server.bean.tree.TreeNode;
import site.server.sending.tree.TreeMessageSendingManager;
import site.server.sending.tree.concurrent.ConcurrentMessageSendingFromAnyNode;
import site.shared.logger.LoggerFactory;

public class ConcurrentMessageSendingProcessFromAnyNode extends
		AbstractMessageSendingProcess {

	private static final Logger LOGGER = LoggerFactory
			.create(ConcurrentMessageSendingProcessFromAnyNode.class);

	public ConcurrentMessageSendingProcessFromAnyNode() {
		super("-ca", new ConcurrentMessageSendingFromAnyNode());
	}

	@Override
	public void logMessageSendingMethod() {
		LOGGER.info("**** SENDING A MESSAGE SIMULTANEOUSLY FROM ANY NODE ****");
	}

	@Override
	public void fullMessageSendingProcess(String[] args, TreeNode[] nodesArray,
			TreeMessageSendingManager messageSendingManager,
			SuperPrinter superPrinter) throws RemoteException,
			InterruptedException {
		int senderIndex = Integer.parseInt(args[3]);
		messageSendingManager.fullMessageSendingProcess(args[2],
				nodesArray[senderIndex], superPrinter, nodesArray);
	}

}
