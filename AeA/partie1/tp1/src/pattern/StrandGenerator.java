package pattern;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import bases.Alphabet;
import bases.PairingsManager;

public class StrandGenerator {
	
	public Strand nextStrand(Strand currentStrand, Alphabet alphabet, PairingsManager pairingsManager) {
		Character[] nextStrandContent = currentStrand.getContent();
		int currentIndex = nextStrandContent.length - 1;
		boolean done = false;
		while ((currentIndex >= 0) && (!done)) {
			final char currentLetter = nextStrandContent[currentIndex];
			final int currentValue = alphabet.getIndex(currentLetter);
			if (currentValue < (alphabet.size() - 1)) {
				final char nextLetter = alphabet.getBases()[currentValue + 1];
				nextStrandContent[currentIndex] = nextLetter;
				done = true;
			} else {
				nextStrandContent[currentIndex] = alphabet.getBases()[0];
				currentIndex--;
			}
		}
		if (currentIndex >= 0) {
			return new Strand(nextStrandContent, pairingsManager);
		} else {
			return null;
		}
	}

	public List<Strand> buildAllStrands(int n, Alphabet alphabet, PairingsManager pairingsManager) {
		final List<Strand> strands = new LinkedList<Strand>();
		char[] firstStrandArray = new char[n];
		Arrays.fill(firstStrandArray, alphabet.getBases()[0]);
		String firstStrandString = new String(firstStrandArray);
		strands.add(new Strand(firstStrandString, pairingsManager));
		Strand currentStrand = strands.get(0);
		while ((currentStrand = nextStrand(currentStrand, alphabet, pairingsManager)) != null) {
			strands.add(currentStrand.clone());
		}
		return strands;
	}

}
