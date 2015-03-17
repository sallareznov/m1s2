package rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;

public class Client {

	public static void main(String[] args) throws MalformedURLException,
			RemoteException, NotBoundException {
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new RMISecurityManager());
		}
		final Participant initiateur = (Participant) Naming.lookup("2");
		System.out.println("Avant élection...");
		System.out.println("élu = " + initiateur.election());
	}

}
