package bases.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class PairingsManager {

	private static final String DELIMITER = ":";
	private List<Pairing> pairings;
	private Set<Character> alphabet;
	
	public PairingsManager() {
		pairings = new LinkedList<Pairing>();
		alphabet = new HashSet<Character>();
	}

	public PairingsManager(String filename) {
		pairings = new LinkedList<Pairing>();
		alphabet = new HashSet<Character>();
		addPairings(filename);
	}

	public void addPairings(String filename) {
		try {
			final BufferedReader reader = new BufferedReader(new FileReader(
					filename));
			String line = null;
			while ((line = reader.readLine()) != null) {
				final StringTokenizer tokenizer = new StringTokenizer(line,
						DELIMITER);
				final char firstBase = tokenizer.nextToken().charAt(0);
				final char secondBase = tokenizer.nextToken().charAt(0);
				final boolean isPerfect = Boolean.parseBoolean(tokenizer
						.nextToken());
				addPairing(new Pairing(firstBase, secondBase, isPerfect));
			}
		} catch (IOException e) {
			System.out.println("Your file doesn't exists or is not well configured");
		}
	}

	public void addPairing(Pairing pairing) {
		pairings.add(pairing);
	}
	
	public Set<Character> getAlphabet() {
		if (alphabet.isEmpty()) {
			for (Pairing pairing : pairings) {
				alphabet.add(pairing.getFirstBase());
				alphabet.add(pairing.getSecondBase());
			}
		}
		return alphabet;
	}

	public Pairing getPairing(char base) throws NonExistentPairingException {
		for (final Pairing pairing : pairings) {
			if (pairing.getFirstBase() == base
					|| pairing.getSecondBase() == base)
				return pairing;
		}
		throw new NonExistentPairingException(base);
	}

	public boolean contains(char base) throws NonExistentPairingException {
		for (final Pairing pairing : pairings) {
			if (pairing.contains(base))
				return true;
		}
		return false;
	}

	public char getComplementary(char base) throws NonExistentPairingException {
		for (final Pairing pairing : pairings) {
			if (base == pairing.getFirstBase())
				return pairing.getSecondBase();
			if (base == pairing.getSecondBase())
				return pairing.getFirstBase();
		}
		throw new NonExistentPairingException(base);
	}

}
