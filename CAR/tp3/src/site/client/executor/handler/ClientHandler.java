package site.client.executor.handler;

import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public interface ClientHandler {

	void execute(String[] args) throws RemoteException,
			InterruptedException, NotBoundException, UnknownHostException;

}
