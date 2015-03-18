package exemple1;

public class ConstructeurAnneau {
	
	public void construitAnneau(Participant ... participants) {
		for (int i = 0 ; i < participants.length ; i++) {
			participants[i].setSuivant(participants[(participants.length + i + 1) % participants.length]);
		}
	}

}
