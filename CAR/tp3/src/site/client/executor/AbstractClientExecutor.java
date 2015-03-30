package site.client.executor;

import java.io.IOException;
import java.rmi.NotBoundException;

import site.client.executor.handler.ClientHandler;
import site.client.reader.SiteReader;

public abstract class AbstractClientExecutor implements ClientExecutor {

	private SiteReader siteReader;
	private ClientHandler handler;

	public AbstractClientExecutor(SiteReader siteReader, ClientHandler handler) {
		this.siteReader = siteReader;
		this.handler = handler;
	}

	@Override
	public boolean accept(ClientExecutorParameters parameters) {
		final SiteReader reader = parameters.getReader();
		return acceptedNodeType.equals(reader.getType());
	}

	@Override
	public void execute(ClientExecutorParameters parameters)
			throws InterruptedException,
			NotBoundException, IOException {
		final String[] args = parameters.getArgs();
		handler.execute(siteReader, args);
	}

}
