package exemple1;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;

public class Client {

	public static void main(String[] args) throws MalformedURLException,
			RemoteException, NotBoundException, UnknownHostException {
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new RMISecurityManager());
		}
		final String key = "rmi://" + InetAddress.getLocalHost().getHostAddress() + "/ParticipantFactory";
		final ParticipantFactory participantFactory = (ParticipantFactory) Naming.lookup(key);
		final Participant participant1 = participantFactory.create(1);
		final Participant participant2 = participantFactory.create(2);
		final Participant participant3 = participantFactory.create(3);
		final Participant participant4 = participantFactory.create(4);
		final Participant participant5 = participantFactory.create(5);
		final ConstructeurAnneau constructeurAnneau = new ConstructeurAnneau();
		constructeurAnneau.construitAnneau(participant2, participant1, participant4, participant3, participant5);
		final ManagerElection managerElection = new ManagerElection();
		System.out.println("élu exemple 1 = " + managerElection.initieElection(participant2));
	}

}
