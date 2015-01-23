package algorithms;

import patterns.Genome;
import patterns.Strand;
import algorithms.util.StrandOccurences;
import bases.Base;

/**
 * Classe representant l'algorithme de recherche naive
 */
public class BruteForceAlgorithm extends Algorithm {

	/**
	 * construit un algorithme de recherche naive
	 * 
	 * @param genome
	 *            le genome sur lequel s'effectuera la recherche
	 */
	public BruteForceAlgorithm(Genome genome) {
		super(genome);
	}

	@Override
	public StrandOccurences findRepetitiveStrand(Strand strand) {
		final StrandOccurences strandOccurences = new StrandOccurences();
		final Base[] genomeBases = getGenome().getBases();
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
