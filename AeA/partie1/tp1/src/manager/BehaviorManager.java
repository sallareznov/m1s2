package manager;

import java.util.LinkedList;
import java.util.List;

public class BehaviorManager<P, R> {

	private List<Behavior<P, R>> behaviors;

	public BehaviorManager() {
		behaviors = new LinkedList<Behavior<P, R>>();
	}

	public void add(Behavior<P, R> behavior) {
		behaviors.add(behavior);
	}

	public R execute(P parameters) {
		for (final Behavior<P, R> behavior : behaviors) {
			if (behavior.accept(parameters)) {
				return behavior.execute(parameters);
			}
		}
		return null;
	}

}
