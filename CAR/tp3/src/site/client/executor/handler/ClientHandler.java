package site.client.executor.handler;

import java.io.IOException;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import site.client.reader.SiteReader;

public interface ClientHandler {

	void execute(SiteReader reader, String[] args) throws RemoteException,
			InterruptedException, NotBoundException, UnknownHostException, IOException;

}
