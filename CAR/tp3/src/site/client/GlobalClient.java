package site.client;

import java.rmi.NotBoundException;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;

public class GlobalClient {
	
	private static final String TREE_NODE = "-tree";
	private static final String GRAPH_NODE = "-graph";

	private GlobalClient() {
	}

	public static void main(String[] args) throws RemoteException,
			NotBoundException, InterruptedException {
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new RMISecurityManager());
		}
		
		if (TREE_NODE.equals(args[0])) {
			new TreeClient().execute();
		}
		else if (GRAPH_NODE.equals(args[0])) {
			new GraphClient().execute();
		}

	}

}
