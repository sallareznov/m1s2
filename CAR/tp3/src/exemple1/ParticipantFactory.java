package exemple1;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ParticipantFactory extends Remote, Serializable {
	
	Participant create(int id) throws RemoteException;

}
