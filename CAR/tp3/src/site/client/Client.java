package site.client;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.util.logging.Logger;

import site.server.AfficheurSite;
import site.server.DataSendingMethod;
import site.server.DataSendingFromAnySite;
import site.server.DataSendingFromTheRootSite;
import site.server.Site;
import site.server.SiteFactory;
import site.shared.LoggerFactory;

public class Client {

	private static final Logger LOGGER = LoggerFactory.create(Client.class);

	public static void main(String[] args) throws UnknownHostException,
			MalformedURLException, RemoteException, NotBoundException,
			InterruptedException {
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new RMISecurityManager());
		}
		final String key = "rmi://"
				+ InetAddress.getLocalHost().getHostAddress() + "/SiteFactory";
		
		// Obtention of the factory at the server
		final SiteFactory siteFactory = (SiteFactory) Naming.lookup(key);
		
		// Construction of the tree-like structure
		final Site site1 = siteFactory.create(1);
		final Site site2 = siteFactory.create(2);
		final Site site3 = siteFactory.create(3);
		final Site site4 = siteFactory.create(4);
		final Site site5 = siteFactory.create(5);
		final Site site6 = siteFactory.create(6);
		site1.setFils(site2, site5);
		site2.setFils(site3, site4);
		site5.setFils(site6);
		
		// Instanciation of the data to send and the site printer 
		final byte[] donnees = { 127, 0, 0, 1, 25, 70 };
		final AfficheurSite afficheurSite = new AfficheurSite();
		
		// Data sending from the root site
		LOGGER.info("**** ENVOI DE DONNEES DEPUIS LA RACINE ****\n");
		site1.setDonnees(donnees);
		LOGGER.info("Avant envoi");
		afficheurSite.affiche(site1, site2, site3, site4, site5, site6);
		final DataSendingMethod envoiRacine = new DataSendingFromTheRootSite();
		envoiRacine.envoieDonnees(site1);
		LOGGER.info("Après envoi");
		afficheurSite.affiche(site1, site2, site3, site4, site5, site6);
		
		// Data resetting
		site1.resetDonnees();
		site2.resetDonnees();
		site3.resetDonnees();
		site4.resetDonnees();
		site5.resetDonnees();
		site6.resetDonnees();
		
		// Data sending from any site
		LOGGER.info("**** ENVOI DE DONNEES DEPUIS N'IMPORTE QUEL SITE ****\n");
		site5.setDonnees(donnees);
		LOGGER.info("Avant envoi");
		afficheurSite.affiche(site1, site2, site3, site4, site5, site6);
		final DataSendingMethod envoiQuelconque = new DataSendingFromAnySite();
		envoiQuelconque.envoieDonnees(site5);
		LOGGER.info("Après envoi");
		afficheurSite.affiche(site1, site2, site3, site4, site5, site6);
	}

}
