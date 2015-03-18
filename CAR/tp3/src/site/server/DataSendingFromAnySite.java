package site.server;


public class DataSendingFromAnySite implements DataSendingMethod {
	
	@Override
	public void envoieDonnees(final Site expediteur)
			throws InterruptedException {
		final Site pere = expediteur.getPere();
		if (pere != null) {
			final Thread thread = new Thread(new Runnable() {

				@Override
				public void run() {
					pere.propageDonnees(expediteur);
				}

			});
			thread.start();
			thread.join();
		}
		final Site[] fils = expediteur.getFils();
		if (fils != null) {
			for (final Site unFils : fils) {
				final Thread thread = new Thread(new Runnable() {

					@Override
					public void run() {
						unFils.propageDonnees(expediteur);
					}

				});
				thread.start();
				thread.join();
			}
		}
	}

}
