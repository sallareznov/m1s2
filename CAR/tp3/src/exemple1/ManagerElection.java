package exemple1;

public class ManagerElection {
	
	public int initieElection(Participant initiateur) {
		final int idInitiateur = initiateur.getId();
		final DonneesElection election = new DonneesElection(idInitiateur, idInitiateur);
		if (initiateur.getSuivant() == null) {
			return initiateur.getId();
		}
		return election(initiateur.getSuivant(), election);
	}
	
	public int election(Participant participant, DonneesElection donneesElection) {
		final int idElu = donneesElection.getIdElu();
		final int idInitiateur = donneesElection.getIdInitiateur();
		final int idParticipant = participant.getId();
		final Participant participantSuivant = participant.getSuivant();
		final int max = Math.max(idParticipant, idElu);
		if (participantSuivant.getId() == idInitiateur) {
			return max;
		}
		donneesElection.setIdElu(max);
		return election(participantSuivant, donneesElection);
	}

}
