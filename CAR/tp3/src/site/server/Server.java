package site.server;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.ExportException;
import java.util.logging.Logger;

import site.server.executor.DefaultServerExecutor;
import site.server.executor.GraphServerExecutor;
import site.server.executor.ServerExecutorParameters;
import site.server.executor.TreeServerExecutor;
import site.shared.behavior.BehaviorManager;
import site.shared.logger.LoggerFactory;

/**
 * A server is a node, and it's defined by its node type and its node id
 */
public class Server {

	private static final Logger LOGGER = LoggerFactory.create(Server.class);
	
	private Server() {
	}

	/**
	 * main method
	 * @param args the arguments
	 * @throws MalformedURLException
	 * @throws RemoteException
	 * @throws UnknownHostException
	 * @throws AlreadyBoundException
	 * @throws InterruptedException
	 * @throws NotBoundException
	 */
	public static void main(String[] args) throws MalformedURLException,
			RemoteException, UnknownHostException, AlreadyBoundException, InterruptedException, NotBoundException {
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new RMISecurityManager());
		}
		System.setProperty("java.rmi.server.hostname", InetAddress.getLocalHost().getHostName());
		try {
			LocateRegistry.createRegistry(1099);
		}
		catch(ExportException e) {
			LOGGER.throwing("Server", "main()", e);
		}
		final BehaviorManager<ServerExecutorParameters> serverExecutorManager = new BehaviorManager<ServerExecutorParameters>();
		serverExecutorManager.addBehavior(new TreeServerExecutor());
		serverExecutorManager.addBehavior(new GraphServerExecutor());
		serverExecutorManager.addBehavior(new DefaultServerExecutor());
		final ServerExecutorParameters parameters = new ServerExecutorParameters(args[0], Integer.parseInt(args[1]));
		serverExecutorManager.execute(parameters);
	}
}
