package site.client;

import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.List;

public class BehaviorManager<P> {

	private List<Behavior<P>> behaviors;

	public BehaviorManager() {
		behaviors = new LinkedList<Behavior<P>>();
	}

	public void addExecutor(Behavior<P> behavior) {
		behaviors.add(behavior);
	}

	public void execute(P parameters) throws RemoteException,
			UnknownHostException, InterruptedException, NotBoundException {
		for (final Behavior<P> behavior : behaviors) {
			if (behavior.accept(parameters)) {
				behavior.execute(parameters);
				return;
			}
		}
	}

}
