package exemple2;

public class DonneesElection {

	private int idElu;
	private int idInitiateur;
	private String sens;
	
	public DonneesElection(int idElu, int idInitiateur, String sens) {
		this.idElu = idElu;
		this.idInitiateur = idInitiateur;
		this.sens = sens;
	}
	
	public int getIdElu() {
		return idElu;
	}
	
	public int getIdInitiateur() {
		return idInitiateur;
	}
	
	public String getSens() {
		return sens;
	}
	
	public void setIdElu(int idElu) {
		this.idElu = idElu;
	}

}
