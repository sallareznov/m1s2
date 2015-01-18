package algorithms;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import patterns.Alphabet;
import patterns.Genome;
import patterns.Strand;
import algorithms.util.StrandOccurences;

/**
 * Classe representant l'algorithme Karp-Rabin
 */
public class KarpRabinAlgorithm extends Algorithm {
	
	private Map<Character, Integer> lettersValues;

	/**
	 * construit l'algorithme Karp-Rabin
	 * @param genome le genome sur lequel s'effectuera la recherche
	 */
	public KarpRabinAlgorithm(Genome genome) {
		super(genome);
		final Alphabet alphabet = genome.getAlphabet();
		final char[] alphabetLetters = alphabet.getLetters();
		lettersValues = new HashMap<Character, Integer>();
		for (int i = 0 ; i < alphabetLetters.length ; i++) {
			lettersValues.put(alphabetLetters[i], i + 1);
		}
	}
	
//	public int h() {
//		
//	}

	@Override
	public List<StrandOccurences> findRepetitiveWords(List<Strand> strand) {
		// TODO Auto-generated method stub
		return null;
	}

}
