package exemple2;

import java.io.Serializable;

public class Participant implements Serializable {

	private static final long serialVersionUID = 5676574371027882392L;
	private int id;
	private Participant voisinGauche;
	private Participant voisinDroit;
	private boolean dejaVisite;

	public Participant(int id) {
		this.id = id;
		dejaVisite = false;
	}

	public int getId() {
		return id;
	}
	
	public Participant getVoisinGauche() {
		return voisinGauche;
	}
	
	public Participant getVoisinDroit() {
		return voisinDroit;
	}
	
	public boolean estDejaVisite() {
		return dejaVisite;
	}

	public void setVoisinGauche(Participant voisin) {
		voisinGauche = voisin;
	}

	public void setVoisinDroit(Participant voisin) {
		voisinDroit = voisin;
	}
	
	public void setDejaVisite(boolean dejaVisite) {
		this.dejaVisite = dejaVisite;
	}

}
