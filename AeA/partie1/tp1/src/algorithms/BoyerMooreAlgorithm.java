package algorithms;

import java.util.HashMap;
import java.util.Map;

import patterns.Genome;
import patterns.Strand;
import algorithms.util.StrandOccurences;
import bases.Base;

/**
 * Classe representant l'algorithme de recherche Boyer-Moore
 */
public class BoyerMooreAlgorithm extends Algorithm {

	// le tableau de bad-matches (nombre de caracteres a "shifter" pour chaque
	// lettre lors d'un no-match
	private Map<Character, Integer> badMatchTable;
	// le tableau des bons suffixes
	private int[] goodSuffix;

	/**
	 * construit un algorithme de Boyer-Moore
	 * 
	 * @param genome
	 *            le genome etudie
	 */
	public BoyerMooreAlgorithm() {
		super();
		badMatchTable = new HashMap<Character, Integer>();
	}

	private void fillGoodSuffixes(Strand strand) {
		goodSuffix = new int[strand.getSize()];
		goodSuffix[goodSuffix.length - 1] = 1;
		final String strandString = strand.toString();
		final int strandLength = strandString.length();
		final int longestEdgeSize = strand.getLongestEdge().getSize();
		for (int i = goodSuffix.length - 2 ; i >= 0; i--) {
			final String strandToCompare = strandString.substring(i + 1, strandLength);
			int j = i + 1;
			int k = goodSuffix.length;
			boolean trouve = false;
			while ((j >= 1) && (!trouve)) {
				final String currentStrand = strandString.substring(j, k);
				if ((currentStrand.equals(strandToCompare)) && (strandString.charAt(j - 1) != strandString.charAt(i))) {
					trouve = true;
					goodSuffix[i] = i - j + 1;
				}
				else {
					j--;
					k--;
				}
			}
			if (j < 1) {
				goodSuffix[i] = strandLength - longestEdgeSize;
			}
		}
	}

	private void fillBadMatches(Genome genome, Strand strand) {
		final String strandString = strand.toString();
		final int strandSize = strand.getSize();
		for (int i = 0; i < strandSize - 1; i++) {
			badMatchTable.put(strandString.charAt(i), strandSize - i - 1);
		}
		badMatchTable.put(strandString.charAt(strandSize - 1), strandSize);
		for (char c : genome.getAlphabet().getLetters()) {
			if (badMatchTable.get(c) == null)
				badMatchTable.put(c, strandSize);
		}
	}

	private void preTreat(Genome genome, Strand strand) {
		fillGoodSuffixes(strand);
		fillBadMatches(genome, strand);
	}

	@Override
	public StrandOccurences findRepetitiveStrand(Genome genome, Strand strand) {
		preTreat(genome, strand);
		final StrandOccurences strandOccurences = new StrandOccurences();
		final Base[] genomeBases = genome.getBases();
		final Base[] strandBases = strand.getContent();
		int i = strandBases.length - 1;
		while (i < genomeBases.length) {
			int genomeWalker = i;
			int strandWalker = strandBases.length - 1;
			while (strandWalker >= 0
					&& genomeBases[genomeWalker]
							.equals(strandBases[strandWalker])) {
				genomeWalker--;
				strandWalker--;
			}
			if (strandWalker < 0) {
				strandOccurences.addIndex(i - strandBases.length + 1);
				i++;
			} else {
				i += Math.max(goodSuffix[strandWalker], badMatchTable.get(genomeBases[i].getWording()) - (strand.getSize() - strandWalker));
			}
		}
		return strandOccurences;
	}

	@Override
	public String toString() {
		return "Algorithme de Boyer-Moore";
	}

}
