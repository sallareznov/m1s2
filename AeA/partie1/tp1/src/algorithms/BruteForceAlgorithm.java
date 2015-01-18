package algorithms;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

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
	 * @param genome le genome sur lequel s'effectuera la recherche
	 */
	public BruteForceAlgorithm(Genome genome) {
		super(genome);
	}
	
	/**
	 * retourne les occurences d'un brin dans le genome
	 * @param strand le brin
	 * @param genome le genome
	 * @return les occurences du brin
	 */
	private StrandOccurences findRepetitiveWord(Strand strand, Genome genome) {
		final StrandOccurences strandOccurences = new StrandOccurences();
		final Base[] genomeBases = genome.getBases();
		final Base[] strandBases = strand.getContent();
		for (int i = 0 ; i < genomeBases.length - strand.getSize() + 1 ; i++) {
			int j = 0;
			while (j < strand.getSize() && genomeBases[j + i].equals(strandBases[j])) {
				j++;
			}
			if (j == strand.getSize())
				strandOccurences.addIndex(i);
		}
		return strandOccurences;
	}

	@Override
	public List<StrandOccurences> findRepetitiveWords(List<Strand> strands) {
		final List<StrandOccurences> strandsOccurences = new LinkedList<StrandOccurences>();
		final Genome genome = getGenome();
		final Iterator<Strand> strandIterator = strands.iterator();
		while (strandIterator.hasNext()) {
			final StrandOccurences strandOccurences = findRepetitiveWord(strandIterator.next(), genome);
			strandsOccurences.add(strandOccurences);
		}
		return strandsOccurences;
	}

}
