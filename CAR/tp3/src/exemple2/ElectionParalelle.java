package exemple2;

public class ElectionParalelle implements Runnable {

	private Participant initiateur;
	private String sens;
	private GestionnaireSens gestionnaireSens;
	private ResultatsElection resultats;

	public ElectionParalelle(Participant initiateur, String sens,
			GestionnaireSens gestionnaireSens, ResultatsElection resultats) {
		this.initiateur = initiateur;
		this.sens = sens;
		this.gestionnaireSens = gestionnaireSens;
		this.resultats = resultats;
	}

	@Override
	public void run() {
		try {
			final int idInitiateur = initiateur.getId();
			final Participant participantSuivant = gestionnaireSens.getSuivant(
					initiateur, sens);
			final DonneesElection donneesElection = new DonneesElection(
					idInitiateur, idInitiateur, sens);
			System.out.println(election(participantSuivant, donneesElection));
			gestionnaireSens.updateResultats(resultats, election(participantSuivant, donneesElection), sens);
			System.out.println(this + " " + resultats.getIdEluGauche());
			System.out.println(this + " " + resultats.getIdEluDroite());
		} catch (SuivantNonTrouveException e) {
		}
		// resultats.setIdEluDroite(election(participantSuivant,
		// donneesElection));
	}

	public int election(Participant participant, DonneesElection donneesElection) throws SuivantNonTrouveException {
		final int idElu = donneesElection.getIdElu();
		final int idInitiateur = donneesElection.getIdInitiateur();
		final int idParticipant = participant.getId();
		final String sens = donneesElection.getSens();
		final Participant participantSuivant = gestionnaireSens.getSuivant(
				initiateur, sens);
		final int max = Math.max(idParticipant, idElu);
		if (participantSuivant.getId() == idInitiateur
				|| participantSuivant.estDejaVisite()) {
			return max;
		}
		donneesElection.setIdElu(max);
		participantSuivant.setDejaVisite(true);
		return election(participantSuivant, donneesElection);
	}

}
