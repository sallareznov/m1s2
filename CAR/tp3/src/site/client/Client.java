package site.client;

import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;

import site.client.executor.ClientExecutorParameters;
import site.client.executor.DefaultClientExecutor;
import site.client.executor.GraphClientExecutor;
import site.client.executor.TreeClientExecutor;
import site.shared.behavior.BehaviorManager;

public class Client {

	private Client() {
	}

	public static void main(String[] args) throws RemoteException,
			NotBoundException, InterruptedException, UnknownHostException {
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new RMISecurityManager());
		}
		final BehaviorManager<ClientExecutorParameters> manager = new BehaviorManager<ClientExecutorParameters>();
		manager.addBehavior(new TreeClientExecutor());
		manager.addBehavior(new GraphClientExecutor());
		manager.addBehavior(new DefaultClientExecutor());
		final ClientExecutorParameters parameters = new ClientExecutorParameters(args[0], args[1], args[2]);
		manager.execute(parameters);
	}

}
