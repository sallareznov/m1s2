package exemple2;



public class ManagerElection {
	
	private ResultatsElection resultats;
	
	public ManagerElection() {
		resultats = new ResultatsElection();
	}
	
	public int initieElection(final Participant initiateur) throws InterruptedException {
//		final Thread electionGauche = new Thread(new Runnable() {
//			
//			@Override
//			public void run() {
//				final int idInitiateur = initiateur.getId();
//				final Participant participantSuivant = getSuivant(initiateur, Sens.GAUCHE);
//				final DonneesElection donneesElection = new DonneesElection(idInitiateur, idInitiateur, Sens.GAUCHE);
//				resultats.setIdEluGauche(election(participantSuivant, donneesElection));
//			}
//		});
//		final Thread electionDroite = new Thread(new Runnable() {
//			
//			@Override
//			public void run() {
//				final int idInitiateur = initiateur.getId();
//				final Participant participantSuivant = getSuivant(initiateur, Sens.DROIT);
//				final DonneesElection donneesElection = new DonneesElection(idInitiateur, idInitiateur, Sens.DROIT);
//				resultats.setIdEluDroite(election(participantSuivant, donneesElection));
//			}
//		});
		final GestionnaireSens gestionnaireSens = new GestionnaireSens();
		gestionnaireSens.ajouteSens(new SensGauche());
		gestionnaireSens.ajouteSens(new SensDroit());
		final ElectionParalelle sousElectionGauche = new ElectionParalelle(initiateur, "GAUCHE", gestionnaireSens, resultats);
		final ElectionParalelle sousElectionDroit = new ElectionParalelle(initiateur, "DROIT", gestionnaireSens, resultats);
		final Thread electionGauche = new Thread(sousElectionGauche);
		final Thread electionDroite = new Thread(sousElectionDroit);
		electionGauche.start();
		electionDroite.start();
		electionGauche.join();
		electionDroite.join();
		return resultats.getIdElu();
	}

}
