package site.shared;

import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public interface Behavior<P> {

	boolean accept(P parameters);

	void execute(P parameters) throws RemoteException, UnknownHostException,
			InterruptedException, NotBoundException;

}
