package algorithms;

import java.util.HashMap;
import java.util.Map;

import pattern.Genome;
import pattern.Strand;
import algorithms.util.StrandOccurences;
import bases.Alphabet;

/**
 * Classe representant l'algorithme Karp-Rabin
 */
public class KarpRabinAlgorithm extends Algorithm {

	// les valeurs des lettres de l'alphabet
	private Map<Character, Integer> lettersValues;
	// le resultat de la fonction de hachage hash() pour le brin recherche
	private int strandHash;

	/**
	 * fonction de hachage
	 * 
	 * @param alphabet
	 * @param bases
	 * @return la valeur
	 */
	private int hash(Alphabet alphabet, Character[] bases) {
		final int alphabetLength = alphabet.size();
		int currentCoeff = bases.length - 1;
		int finalHash = 0;
		for (final char base : bases) {
			finalHash += lettersValues.get(base)
					* Math.pow(alphabetLength, currentCoeff);
			currentCoeff--;
		}
		return finalHash;
	}

	/**
	 * pretraitement de l'algorithme
	 * 
	 * @param genome
	 * @param strand
	 */
	private void preTreat(Strand strand, Alphabet alphabet) {
		final Character[] alphabetLetters = alphabet.getBases();
		lettersValues = new HashMap<Character, Integer>();
		for (int i = 0; i < alphabetLetters.length; i++) {
			lettersValues.put(alphabetLetters[i], i + 1);
		}
		strandHash = hash(alphabet, strand.getContent());
	}

	/**
	 * verifie qu'on a trouve une occurence
	 * 
	 * @param basesToCompare
	 * @param strandBases
	 * @return
	 */
	private boolean occurenceFound(Character[] basesToCompare, Character[] strandBases) {
		for (int i = 0; i < strandBases.length; i++) {
			if (basesToCompare[i] != (strandBases[i]))
				return false;
		}
		return true;
	}

	@Override
	public StrandOccurences findRepetitiveStrand(Genome genome, Strand strand,
			Alphabet alphabet) {
		preTreat(strand, alphabet);
		resetNbComparisons();
		final StrandOccurences strandOccurences = new StrandOccurences();
		final Character[] basesToCompare = new Character[strand.getSize()];
		for (int i = 0; i < genome.getSize() - strand.getSize() + 1; i++) {
			System.arraycopy(genome.getContent(), i, basesToCompare, 0,
					strand.getSize());
			final int basesHash = hash(alphabet, basesToCompare);
			incrNbComparisons();
			if ((basesHash == strandHash)
					&& (occurenceFound(basesToCompare, strand.getContent()))) {
				strandOccurences.addIndex(i);
			}
		}
		return strandOccurences;
	}

	@Override
	public String toString() {
		return "Algorithme de Karp-Rabin";
	}

}
