package site.server.executor;

import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.logging.Logger;

import site.shared.logger.LoggerFactory;

public class DefaultServerExecutor implements ServerExecutor {
	
	private static final Logger LOGGER = LoggerFactory.create(DefaultServerExecutor.class);

	@Override
	public boolean accept(ServerExecutorParameters parameters) {
		return true;
	}
	
	@Override
	public void execute(ServerExecutorParameters parameters)
			throws RemoteException, UnknownHostException, InterruptedException,
			NotBoundException {
		LOGGER.info("java -Djava.security.policy=java.policy -jar server.jar [-tree|-graph] <id>");
		LOGGER.info("\tcrée un noeud qui servira de serveur");
		LOGGER.info("\t[-tree|-graph] : le type de noeud qui sera créé");
		LOGGER.info("\t<id> : l'identifiant du noeud");
	}

}
