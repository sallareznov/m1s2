package exemple2;

import java.util.LinkedList;
import java.util.List;

public class GestionnaireSens {
	
	private List<Sens> ensembleSens;
	
	public GestionnaireSens() {
		ensembleSens = new LinkedList<Sens>();
	}
	
	public void ajouteSens(Sens sens) {
		ensembleSens.add(sens);
	}
	
	public Participant getSuivant(Participant participant, String sens) throws SuivantNonTrouveException {
		for (final Sens unSens : ensembleSens) {
			if (unSens.accept(sens))
				return unSens.getSuivant(participant);
		}
		throw new SuivantNonTrouveException();
	}
	
	public void updateResultats(ResultatsElection resultats, int idElu, String sens) {
		for (final Sens unSens : ensembleSens) {
			if (unSens.accept(sens))
				unSens.updateResultats(resultats, idElu);
		}
	}

}
