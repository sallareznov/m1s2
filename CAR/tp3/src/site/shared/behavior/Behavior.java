package site.shared.behavior;

import java.io.IOException;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * A behavior, capable of accepting parameters and executing context
 * @param <P> the parameters
 */
public interface Behavior<P> {

	/**
	 * checks if the behavior accepts the parameters
	 * @param parameters the parameters
	 * @return <code>true</code> if the behavior accepts the parameters
	 */
	boolean accept(P parameters);

	/**
	 * executes the behavior with the parameters
	 * @param parameters the parameters
	 * @throws RemoteException
	 * @throws UnknownHostException
	 * @throws InterruptedException
	 * @throws NotBoundException
	 * @throws IOException 
	 */
	void execute(P parameters) throws RemoteException, UnknownHostException,
			InterruptedException, NotBoundException, IOException;

}
