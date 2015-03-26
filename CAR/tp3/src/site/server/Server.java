package site.server;

import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.AlreadyBoundException;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.ExportException;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Logger;

import site.server.bean.tree.TreeNode;
import site.server.bean.tree.TreeNodeImpl;
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
		try {
			LocateRegistry.createRegistry(1099);
		}
		catch(ExportException e) {
			LOGGER.throwing("Server", "main()", e);
		}
		final int clientId = Integer.parseInt(args[0]);
		final TreeNode treeNode = new TreeNodeImpl(clientId);
		final TreeNode node = (TreeNode) UnicastRemoteObject.toStub(treeNode);
		LocateRegistry.getRegistry(1099).rebind("node" + clientId, node);
		LOGGER.info("Hello ! I'm the remote server");
	}

}
