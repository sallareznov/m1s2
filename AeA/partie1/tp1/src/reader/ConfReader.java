package reader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

import bases.util.Alphabet;
import bases.util.Pairing;
import bases.util.PairingsManager;

public class ConfReader {

	private Alphabet alphabet;
	private PairingsManager pairingsManager;

	public ConfReader() {
		alphabet = new Alphabet();
		pairingsManager = new PairingsManager();
	}

	public Alphabet getAlphabet() {
		return alphabet;
	}

	public PairingsManager getPairingsManager() {
		return pairingsManager;
	}

	private void remind() {
		System.err.println("Fichier de la forme : ");
		System.err.println("<alphabet> (Ex : ACGT)");
		System.err
				.println("<une paire par ligne [base1|base2|perfection]> (Ex : A|T|true)");
	}

	private void fillAlphabet(BufferedReader reader) throws IOException {
		final String line = reader.readLine();
		for (int i = 0; i < line.length(); i++) {
			final char baseToAdd = line.charAt(i);
			alphabet.addBase(baseToAdd);
		}
	}

	public void read(String filename) throws FileNotFoundException {
		try {
			final BufferedReader reader = new BufferedReader(new FileReader(
					filename));
			fillAlphabet(reader);
			String line = null;
			while ((line = reader.readLine()) != null) {
				final StringTokenizer tokenizer = new StringTokenizer(line, "|");
				final char firstLetter = tokenizer.nextToken().charAt(0);
				final char secondLetter = tokenizer.nextToken().charAt(0);
				if (alphabet.contains(firstLetter)
						&& (alphabet.contains(secondLetter))) {
					final boolean isPerfect = Boolean.parseBoolean(tokenizer
							.nextToken());
					pairingsManager.addPairing(new Pairing(firstLetter, secondLetter, isPerfect));
				}
			}
		} catch (IOException e) {
			remind();
			return;
		}
	}

}
