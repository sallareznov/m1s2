package manager;

public interface Behavior<P, R> {
	
	boolean accept(P parameters);
	
	R execute(P parameters);

}
