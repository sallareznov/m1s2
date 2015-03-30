package site.server.executor;

import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.logging.Logger;

import site.shared.logger.LoggerFactory;

public abstract class AbstractServerExecutor implements ServerExecutor {
	
	private static final Logger LOGGER = LoggerFactory.create(AbstractServerExecutor.class);
	private String acceptedNodeType;
	
	public AbstractServerExecutor(String acceptedNodeType) {
		this.acceptedNodeType = acceptedNodeType;
	}

	@Override
	public boolean accept(ServerExecutorParameters parameters) {
		final String nodeType = parameters.getNodeType();
		return acceptedNodeType.equals(nodeType);
	}

	@Override
	public void execute(ServerExecutorParameters parameters)
			throws RemoteException, UnknownHostException, InterruptedException,
			NotBoundException {
		createAndRebind(parameters);
		LOGGER.info("Hello ! My name is #Node " + parameters.getNodeId() + ". I'm a remote server");
		LOGGER.info("I don't have any message right now");
	}
	
	public abstract void createAndRebind(ServerExecutorParameters parameters) throws RemoteException;

}
