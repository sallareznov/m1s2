package main;

import bases.util.Pairing;
import bases.util.PairingsManager;

public class Main {
	
	public static void main(String[] args) {
		final PairingsManager pairingsManager = new PairingsManager();
		pairingsManager.addPairing(new Pairing('A', 'U', true));
		pairingsManager.addPairing(new Pairing('G', 'C', true));
		pairingsManager.addPairing(new Pairing('G', 'U', true));
	}

}
