package algorithms;

import java.util.HashMap;
import java.util.Map;

import bases.Base;
import patterns.Genome;
import patterns.Strand;
import algorithms.util.StrandOccurences;

/**
 * Classe representant l'algorithme de recherche Boyer-Moore 
 */
public class BoyerMooreAlgorithm extends Algorithm {
	
	private Map<Character, Integer> badMatchTable;

	/**
	 * construit un algorithme de Boyer-Moore
	 * @param genome le genome etudie
	 */
	public BoyerMooreAlgorithm(Genome genome) {
		super(genome);
		badMatchTable = new HashMap<Character, Integer>();
	}
	
	private void preTreat(Strand strand) {
		final String strandString = strand.toString();
		final int strandSize = strand.getSize();
		for (int i = 0 ; i < strandSize - 1 ; i++) {
			badMatchTable.put(strandString.charAt(i), strandSize - i - 1);
		}
		badMatchTable.put(strandString.charAt(strandSize - 1), strandSize);
		for (char c : getGenome().getAlphabet().getLetters()) {
			if (badMatchTable.get(c) == null)
				badMatchTable.put(c, strandSize);
		}
	}

	@Override
	public StrandOccurences findRepetitiveStrand(Strand strand) {
		preTreat(strand);
		final StrandOccurences strandOccurences = new StrandOccurences();
		final Base[] genomeBases = getGenome().getBases();
		final Base[] strandBases = strand.getContent();
		int i = strandBases.length - 1;
		while (i < genomeBases.length) {
			int genomeWalker = i;
			int strandWalker = strandBases.length - 1;
			while (strandWalker >= 0 && genomeBases[genomeWalker].equals(strandBases[strandWalker])) {
				genomeWalker--;
				strandWalker--;
			}
			if (strandWalker < 0) {
				strandOccurences.addIndex(i - strandBases.length + 1);
				i += 1;
			}
			else {
				i += badMatchTable.get(genomeBases[i].getWording());
			}
		}
		return strandOccurences;
	}

}
