package rmi;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Participant extends Remote, Serializable {
	
	int getId() throws RemoteException;
	
	Participant getSuivant() throws RemoteException;
	
	void setSuivant(Participant suivant) throws RemoteException;

	int election(int idElu, int idInitiateur) throws RemoteException;
	
	int election() throws RemoteException;
	
}
