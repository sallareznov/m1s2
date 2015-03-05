package algorithms;

import pattern.Genome;
import pattern.Strand;
import algorithms.util.StrandOccurences;
import bases.Alphabet;

/**
 * Classe representant l'algorithme de recherche naive
 */
public class BruteForceAlgorithm extends Algorithm {

	@Override
	public StrandOccurences findRepetitiveStrand(Genome genome, Strand strand,
			Alphabet alphabet) {
		resetNbComparisons();
		final StrandOccurences strandOccurences = new StrandOccurences();
		final Character[] genomeBases = genome.getContent();
		final Character[] strandBases = strand.getContent();
		for (int i = 0; i < genomeBases.length - strand.getSize() + 1; i++) {
			int j = 0;
			incrNbComparisons();
			while (j < strand.getSize()
					&& genomeBases[j + i] == (strandBases[j])) {
				j++;
			}
			if (j == strand.getSize())
				strandOccurences.addIndex(i);
		}
		return strandOccurences;
	}

	@Override
	public String toString() {
		return "Algorithme naif (BruteForce)";
	}

}
