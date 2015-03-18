package site.server;

import java.io.Serializable;
import java.util.logging.Logger;

import site.shared.LoggerFactory;

public class Site implements Serializable {

	private static final long serialVersionUID = 7038905654437218863L;
	private int id;
	private Site pere;
	private Site[] fils;
	private byte[] donnees;
	private static final Logger LOGGER = LoggerFactory.create(Site.class);

	public Site() {
	}

	public Site(int id) {
		this.id = id;
		pere = null;
		fils = null;
		donnees = null;
	}

	public int getId() {
		return id;
	}

	public Site getPere() {
		return pere;
	}

	public Site[] getFils() {
		return fils;
	}

	public byte[] getDonnees() {
		return donnees;
	}

	public void setPere(Site pere) {
		this.pere = pere;
	}

	public void setFils(Site... fils) {
		this.fils = fils;
		for (final Site unFils : fils) {
			unFils.setPere(this);
		}
	}

	public void setDonnees(byte[] donnees) {
		this.donnees = donnees;
	}

	public void resetDonnees() {
		donnees = null;
	}

	public void propageDonnees(Site expediteur) {
		LOGGER.info("Site " + id + " : " + "Reception de donnees venant de Site " + expediteur.getId());
		setDonnees(expediteur.getDonnees());
		if (fils == null) {
			return;
		}
		for (final Site unFils : fils) {
			if (!unFils.equals(expediteur)) {
				LOGGER.info("Site " + id + " : " + "Propagation des donnees vers Site " + unFils.getId());
				unFils.propageDonnees(this);
			}
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (obj.getClass() != getClass()) {
			return false;
		}
		final Site site = (Site) obj;
		return site.getId() == id;
	}

}
