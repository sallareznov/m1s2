package exemple2;

public class SensGauche implements Sens {

	@Override
	public boolean accept(String sens) {
		return "GAUCHE".equals(sens);
	}

	@Override
	public Participant getSuivant(Participant participant) {
		return participant.getVoisinGauche();
	}

	@Override
	public void updateResultats(ResultatsElection resultats, int idElu) {
		resultats.setIdEluGauche(idElu);
	}

}
