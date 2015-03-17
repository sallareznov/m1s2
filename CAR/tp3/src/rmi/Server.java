package rmi;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class Server {

	public static void main(String[] args) throws RemoteException,
			MalformedURLException, AlreadyBoundException {
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new RMISecurityManager());
		}
		LocateRegistry.createRegistry(1099);
		final Participant participant1 = new ParticipantImpl(1);
		final Participant participant2 = new ParticipantImpl(2);
		final Participant participant3 = new ParticipantImpl(3);
		final Participant participant4 = new ParticipantImpl(4);
		final Participant participant5 = new ParticipantImpl(5);
		participant2.setSuivant(participant1);
		participant1.setSuivant(participant4);
		participant4.setSuivant(participant3);
		participant3.setSuivant(participant5);
		participant5.setSuivant(participant2);
		Naming.bind("1", participant1);
		Naming.bind("4", participant4);
		Naming.bind("3", participant3);
		Naming.bind("5", participant5);
		Naming.bind("2", participant2);
	}
}
