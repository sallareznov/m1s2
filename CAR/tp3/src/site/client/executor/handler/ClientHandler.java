package site.client.executor.handler;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public interface ClientHandler {

	void execute(String serverAddress, String message) throws RemoteException,
			InterruptedException, NotBoundException;

}
