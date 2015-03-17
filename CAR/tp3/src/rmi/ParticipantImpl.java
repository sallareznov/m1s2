package rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ParticipantImpl extends UnicastRemoteObject implements Participant {
	
	private static final long serialVersionUID = -7643442419969481080L;
	private int id;
	private Participant suivant;
	
	public ParticipantImpl() throws RemoteException {
	}
	
	public ParticipantImpl(int id) throws RemoteException {
		this.id = id;
	}
	
	@Override
	public int getId() throws RemoteException {
		return id;
	}
	
	@Override
	public Participant getSuivant() throws RemoteException {
		return suivant;
	}
	
	@Override
	public void setSuivant(Participant suivant) throws RemoteException {
		this.suivant = suivant;
	}

	@Override
	public int election(int idElu, int idInitiateur) throws RemoteException {
		int max = Math.max(id, idElu);
		if (suivant.getId() == idInitiateur) {
			System.out.println("Fin de l'élection");
			return max;
		}
		return suivant.election(max, idInitiateur);
	}

	@Override
	public int election() throws RemoteException {
		System.out.println("Election en cours...");
		return election(id, id);
	}

}
