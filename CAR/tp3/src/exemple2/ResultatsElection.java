package exemple2;

public class ResultatsElection {
	
	private int idEluGauche;
	private int idEluDroite;
	
	public synchronized int getIdEluGauche() {
		return idEluGauche;
	}
	
	public synchronized int getIdEluDroite() {
		return idEluDroite;
	}
	
	public synchronized int getIdElu() {
		return Math.max(idEluGauche, idEluDroite);
	}
	
	public synchronized void setIdEluGauche(int idEluGauche) {
		this.idEluGauche = Math.max(this.idEluGauche, idEluGauche);
	}
	
	public synchronized void setIdEluDroite(int idEluDroite) {
		this.idEluDroite = Math.max(this.idEluDroite, idEluDroite);
	}

}
