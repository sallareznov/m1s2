package site.client;

import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class TreeClientExecutor implements ClientExecutor {

	@Override
	public boolean accept(ClientExecutorParameters parameters) {
		return "-tree".equals(parameters.getNodeType());
	}

	@Override
	public void execute(ClientExecutorParameters parameters)
			throws RemoteException, UnknownHostException, InterruptedException,
			NotBoundException {
		final String serverAddress = parameters.getServerAddress();
		new TreeClient().execute(serverAddress);
	}

}
