package site.client.executor.handler;

import java.rmi.RemoteException;
import java.util.logging.Logger;

import site.server.SuperPrinter;
import site.server.bean.tree.TreeNode;
import site.server.sending.tree.TreeMessageSendingManager;
import site.server.sending.tree.sequential.SequentialMessageSendingFromTheRootNode;
import site.shared.logger.LoggerFactory;

public class SequentialMessageSendingProcessFromTheRootNode extends
		AbstractMessageSendingProcess {

	private static final Logger LOGGER = LoggerFactory
			.create(SequentialMessageSendingProcessFromTheRootNode.class);

	public SequentialMessageSendingProcessFromTheRootNode() {
		super("-sr", new SequentialMessageSendingFromTheRootNode());
	}

	@Override
	public void logMessageSendingMethod() {
		LOGGER.info("**** SENDING A MESSAGE SEQUENTIALLY FROM THE ROOT NODE ****");
	}

	@Override
	public void fullMessageSendingProcess(String[] args, TreeNode[] nodesArray,
			TreeMessageSendingManager messageSendingManager,
			SuperPrinter superPrinter) throws RemoteException,
			InterruptedException {
		messageSendingManager.fullMessageSendingProcess(args[2],
				nodesArray[0], superPrinter, nodesArray);
	}

}
