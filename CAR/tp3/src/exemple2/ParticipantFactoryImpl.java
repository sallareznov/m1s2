package exemple2;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ParticipantFactoryImpl extends UnicastRemoteObject implements ParticipantFactory {
	
	private static final long serialVersionUID = -4416221819431683048L;

	public ParticipantFactoryImpl() throws RemoteException {
	}

	@Override
	public Participant create(int id) throws RemoteException {
		return new Participant(id);
	}

}
