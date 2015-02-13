package bases.util;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class PairingManager {
	
	private List<Pairing> pairings;
	
	public PairingManager() {
		pairings = new LinkedList<Pairing>();
	}
	
	public void addPairing(Pairing pairing) {
		pairings.add(pairing);
	}
	
	public char getComplementary(char base) {
		final Iterator<Pairing> pairingsIterator = pairings.iterator();
		while (pairingsIterator.hasNext()) {
			final Pairing currentPairing = pairingsIterator.next();
			if (base == currentPairing.getFirstBase())
				return currentPairing.getFirstBase();
			if (base == currentPairing.getSecondBase())
				return currentPairing.getSecondBase();
		}
		return (char) -1;
	}

}
