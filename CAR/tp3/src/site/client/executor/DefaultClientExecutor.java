package site.client.executor;

import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.logging.Logger;

import site.shared.logger.LoggerFactory;

public class DefaultClientExecutor implements ClientExecutor {
	
	private static final Logger LOGGER = LoggerFactory.create(DefaultClientExecutor.class);

	@Override
	public boolean accept(ClientExecutorParameters parameters) {
		return true;
	}

	@Override
	public void execute(ClientExecutorParameters parameters)
			throws RemoteException, UnknownHostException, InterruptedException,
			NotBoundException {
		LOGGER.info("java -Djava.security.policy=java.policy -jar client.jar [-tree|-graph] <server_hostname> <message>");
		LOGGER.info("\tlance un client");
		LOGGER.info("\t[-tree|-graph] : le type de noeud que gère le serveur");
		LOGGER.info("\t<server_hostname> : le nom du serveur distant");
		LOGGER.info("\t<message> : le message qui sera envoyé");
	}

}
