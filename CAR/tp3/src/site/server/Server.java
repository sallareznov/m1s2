package site.server;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.AlreadyBoundException;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.ExportException;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Logger;

import site.server.bean.graph.GraphNode;
import site.server.bean.graph.GraphNodeImpl;
import site.server.bean.tree.TreeNode;
import site.server.bean.tree.TreeNodeImpl;
import site.shared.LoggerFactory;

public class Server {

	private static final Logger LOGGER = LoggerFactory.create(Server.class);
	private static final String TREE_NODE = "-tree";
	private static final String GRAPH_NODE = "-graph";
	
	private Server() {
	}
	
	private static void usage() {
		LOGGER.info("java -Djava.security.policy=java.policy -jar server.jar [-tree|-graph] <id>");
	}

	public static void main(String[] args) throws MalformedURLException,
			RemoteException, UnknownHostException, AlreadyBoundException {
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
		final int clientId = Integer.parseInt(args[1]);
		if (TREE_NODE.equals(args[0])) {
			final TreeNode treeNode = new TreeNodeImpl(clientId);
			final TreeNode node = (TreeNode) UnicastRemoteObject.toStub(treeNode);
			LocateRegistry.getRegistry(1099).rebind("node" + clientId, node);
		}
		else if (GRAPH_NODE.equals(args[0])) {
			final GraphNode graphNode = new GraphNodeImpl(clientId);
			final GraphNode node = (GraphNode) UnicastRemoteObject.toStub(graphNode);
			LocateRegistry.getRegistry(1099).rebind("node" + clientId, node);
		}
		else {
			usage();
			return;
		}
		LOGGER.info("Hello ! My name is #Node " + clientId + ". I'm a remote server");
		LOGGER.info("I don't have any message right now");
	}
}
