package exemple2;

public class ConstructeurAnneau {
	
	public void construitAnneau(Participant ... participants) {
		for (int i = 0 ; i < participants.length ; i++) {
			participants[i].setVoisinDroit(participants[(participants.length + i + 1) % participants.length]);
			participants[i].setVoisinGauche(participants[(participants.length + i - 1) % participants.length]);
		}
	}

}