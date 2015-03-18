package site.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface SiteFactory extends Remote {
	
	Site create(int id) throws RemoteException;

}
