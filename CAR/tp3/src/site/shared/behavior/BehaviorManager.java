package site.shared.behavior;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.util.LinkedList;
import java.util.List;

/**
 * Manages behavior defined by their parameters
 * 
 * @param <P>
 *            the parameters of the behaviors
 */
public class BehaviorManager<P> {

	private List<Behavior<P>> behaviors;

	/**
	 * Default constructor
	 */
	public BehaviorManager() {
		behaviors = new LinkedList<Behavior<P>>();
	}

	/**
	 * adds a behavior to the list of managed behaviors
	 * 
	 * @param behavior
	 *            the behavior to add
	 */
	public void addBehavior(Behavior<P> behavior) {
		behaviors.add(behavior);
	}

	/**
	 * executes the manager by looking the suitable behavior to call
	 * 
	 * @param parameters
	 *            the parameters to pass to the behaviors
	 * @throws InterruptedException
	 * @throws NotBoundException
	 * @throws IOException 
	 */
	public void execute(P parameters) throws InterruptedException, NotBoundException, IOException {
		// the manager iterates on every behavior and executes the first one
		// which accepts the parameters
		for (final Behavior<P> behavior : behaviors) {
			if (behavior.accept(parameters)) {
				behavior.execute(parameters);
				return;
			}
		}
	}

}
