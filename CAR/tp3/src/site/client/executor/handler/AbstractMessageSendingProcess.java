package site.client.executor.handler;

import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import site.server.SuperPrinter;
import site.server.bean.tree.TreeNode;
import site.server.sending.tree.TreeMessageSendingManager;
import site.server.sending.tree.TreeMessageSendingMethod;

public abstract class AbstractMessageSendingProcess implements
		MessageSendingProcess {

	private String acceptedMessageSendingMethodArgument;
	private TreeMessageSendingMethod messageSendingMethod;

	public AbstractMessageSendingProcess(String messageSendingMethodArgument,
			TreeMessageSendingMethod messageSendingMethod) {
		this.acceptedMessageSendingMethodArgument = messageSendingMethodArgument;
		this.messageSendingMethod = messageSendingMethod;
	}

	@Override
	public boolean accept(MessageSendingProcessParameters parameters) {
		final String[] args = parameters.getArgs();
		return acceptedMessageSendingMethodArgument.equals(args[3]);
	}

	@Override
	public void execute(MessageSendingProcessParameters parameters)
			throws RemoteException, UnknownHostException, InterruptedException,
			NotBoundException {
		final TreeMessageSendingManager messageSendingManager = new TreeMessageSendingManager();
		final SuperPrinter superPrinter = new SuperPrinter();
		final TreeNode[] nodesArray = parameters.getNodes();
		logMessageSendingMethod();
		messageSendingManager.setMessageSendingMethod(messageSendingMethod);
		messageSendingManager.resetDatas(nodesArray);
		fullMessageSendingProcess(parameters.getArgs(), nodesArray, messageSendingManager, superPrinter);
	}

	public abstract void fullMessageSendingProcess(String[]args, TreeNode[] nodesArray,
			TreeMessageSendingManager messageSendingManager,
			SuperPrinter superPrinter) throws RemoteException, InterruptedException;

	public abstract void logMessageSendingMethod();

}
