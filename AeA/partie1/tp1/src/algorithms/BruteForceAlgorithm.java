package algorithms;

import patterns.Genome;
import patterns.Strand;
import algorithms.util.StrandOccurences;
import bases.Base;

/**
 * Classe representant l'algorithme de recherche naive
 */
public class BruteForceAlgorithm extends Algorithm {
	
	@Override
	public StrandOccurences findRepetitiveStrand(Genome genome, Strand strand) {
		final StrandOccurences strandOccurences = new StrandOccurences();
		final Base[] genomeBases = genome.getBases();
		final Base[] strandBases = strand.getContent();
		for (int i = 0; i < genomeBases.length - strand.getSize() + 1; i++) {
			int j = 0;
			while (j < strand.getSize()
					&& genomeBases[j + i].equals(strandBases[j])) {
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
