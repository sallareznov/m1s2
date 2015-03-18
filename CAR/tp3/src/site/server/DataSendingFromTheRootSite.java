package site.server;


public class DataSendingFromTheRootSite implements DataSendingMethod {
	
	@Override
	public void envoieDonnees(final Site expediteur) throws InterruptedException {
		final Site[] fils = expediteur.getFils();
		if (fils == null) {
			return;
		}
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
