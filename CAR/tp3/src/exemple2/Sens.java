package exemple2;

public interface Sens {
	
	boolean accept(String sens);
	
	Participant getSuivant(Participant participant);
	
	void updateResultats(ResultatsElection resultats, int idElu);

}
