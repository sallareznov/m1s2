package site.server;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Logger;

import site.shared.LoggerFactory;

public class Server {
	
	private static final Logger LOGGER = LoggerFactory.create(Server.class);

	private Server() {
	}

	public static void main(String[] args) throws MalformedURLException,
			RemoteException, UnknownHostException, AlreadyBoundException {
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new RMISecurityManager());
		}
		LocateRegistry.createRegistry(1099);
		LOGGER.info("Hello ! I'm the remote server");
		final SiteFactoryImpl siteFactoryImpl = new SiteFactoryImpl();
		final SiteFactory factory = (SiteFactory) UnicastRemoteObject
				.toStub(siteFactoryImpl);
		Naming.bind("rmi://" + InetAddress.getLocalHost().getHostAddress()
				+ "/SiteFactory", factory);
	}

}
