package site.client.executor;

import java.io.IOException;
import java.rmi.NotBoundException;

public abstract class AbstractClientExecutor implements ClientExecutor {

	private String acceptedType;

	public AbstractClientExecutor(String acceptedType) {
		this.acceptedType = acceptedType;
	}

	@Override
	public boolean accept(ClientExecutorParameters parameters) {
		final String nodeType = parameters.getNodeType();
		return acceptedType.equals(nodeType);
	}

	@Override
	public abstract void execute(ClientExecutorParameters parameters)
			throws InterruptedException,
			NotBoundException, IOException;

}
