package exemple2;

public class SensDroit implements Sens {

	@Override
	public boolean accept(String sens) {
		return "DROIT".equals(sens);
	}

	@Override
	public Participant getSuivant(Participant participant) {
		return participant.getVoisinDroit();
	}

	@Override
	public void updateResultats(ResultatsElection resultats, int idElu) {
		resultats.setIdEluDroite(idElu);
	}

}
