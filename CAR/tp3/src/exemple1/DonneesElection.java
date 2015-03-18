package exemple1;

public class DonneesElection {
	
	private int idElu;
	private int idInitiateur;
	
	public DonneesElection(int idElu, int idInitiateur) {
		this.idElu = idElu;
		this.idInitiateur = idInitiateur;
	}
	
	public int getIdElu() {
		return idElu;
	}
	
	public int getIdInitiateur() {
		return idInitiateur;
	}
	
	public void setIdElu(int idElu) {
		this.idElu = idElu;
	}

}
