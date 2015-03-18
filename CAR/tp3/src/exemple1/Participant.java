package exemple1;

import java.io.Serializable;


public class Participant implements Serializable {
	
	private static final long serialVersionUID = -7643442419969481080L;
	private int id;
	private Participant suivant;
	
	public Participant() {
	}
	
	public Participant(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public Participant getSuivant() {
		return suivant;
	}
	
	public void setSuivant(Participant suivant) {
		this.suivant = suivant;
	}

}
