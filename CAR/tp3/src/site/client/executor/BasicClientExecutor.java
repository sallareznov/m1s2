package site.client.executor;

import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import site.client.ClientHandler;

public abstract class BasicClientExecutor implements ClientExecutor {

	private String acceptedNodeType;
	private ClientHandler handler;

	public BasicClientExecutor(String acceptedOption, ClientHandler handler) {
		this.acceptedNodeType = acceptedOption;
		this.handler = handler;
	}

	@Override
	public boolean accept(ClientExecutorParameters parameters) {
		final String nodeType = parameters.getNodeType();
		return acceptedNodeType.equals(nodeType);
	}

	@Override
	public void execute(ClientExecutorParameters parameters)
			throws RemoteException, UnknownHostException, InterruptedException,
			NotBoundException {
		final String serverAddress = parameters.getServerAddress();
		final String message = parameters.getMessage();
		handler.execute(serverAddress, message);
	}

}
