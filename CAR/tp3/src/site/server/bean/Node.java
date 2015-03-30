package site.server.bean;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Node extends Remote {

	int getId() throws RemoteException;
	
	String getMessage() throws RemoteException;

	void setMessage(String message) throws RemoteException;

	void resetMessage() throws RemoteException;
	
	String printId() throws RemoteException;
	
	String getAllInfos() throws RemoteException;
	
}
