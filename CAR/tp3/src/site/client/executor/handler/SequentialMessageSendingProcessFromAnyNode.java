package site.client.executor.handler;

import java.rmi.RemoteException;
import java.util.logging.Logger;

import site.server.SuperPrinter;
import site.server.bean.tree.TreeNode;
import site.server.sending.tree.TreeMessageSendingManager;
import site.server.sending.tree.sequential.SequentialMessageSendingFromAnyNode;
import site.shared.logger.LoggerFactory;

public class SequentialMessageSendingProcessFromAnyNode extends
		AbstractMessageSendingProcess {

	private static final Logger LOGGER = LoggerFactory
			.create(SequentialMessageSendingProcessFromAnyNode.class);

	public SequentialMessageSendingProcessFromAnyNode() {
		super("-sa", new SequentialMessageSendingFromAnyNode());
	}

	@Override
	public void logMessageSendingMethod() {
		LOGGER.info("**** SENDING A MESSAGE SEQUENTIALLY FROM ANY NODE ****");
	}

	@Override
	public void fullMessageSendingProcess(String[] args, TreeNode[] nodesArray,
			TreeMessageSendingManager messageSendingManager,
			SuperPrinter superPrinter) throws RemoteException,
			InterruptedException {
		int senderIndex = Integer.parseInt(args[4]) - 1;
		messageSendingManager.fullMessageSendingProcess(args[2],
				nodesArray[senderIndex], superPrinter, nodesArray);
	}

}
