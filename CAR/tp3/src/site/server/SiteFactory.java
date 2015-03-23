package site.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface SiteFactory extends Remote {
	
	TreeSite create(int id) throws RemoteException;

}
