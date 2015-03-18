package site.server;

import java.util.logging.Logger;

import site.shared.LoggerFactory;

public class AfficheurSite {
	
	private static final Logger LOGGER = LoggerFactory.create(AfficheurSite.class);

	public void affiche(Site site) {
		final StringBuilder builder = new StringBuilder();
		builder.append("Site ");
		builder.append(site.getId());
		builder.append(" : ");
		builder.append("\n");
		builder.append("donnees = ");
		final byte[] donnees = site.getDonnees();
		if (donnees == null) {
			builder.append("null");
		} else {
			builder.append("[");
			for (int i = 0; i < donnees.length - 1; i++) {
				builder.append(donnees[i]);
				builder.append("; ");
			}
			builder.append(donnees[donnees.length - 1]);
			builder.append("]");
		}
		LOGGER.info(builder.toString());
	}
	
	public void affiche(Site ... sites) {
		for (final Site site : sites) {
			affiche(site);
		}
	}

}
