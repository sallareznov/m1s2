package algorithms;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import patterns.Genome;
import patterns.Strand;
import algorithms.util.StrandOccurences;

/**
 * Classe abstraite representant un algorithme de recherche
 */
public abstract class Algorithm {
	
	private int _nbComparisons;
	
	public Algorithm() {
		_nbComparisons = 0;
	}
	
	public int getNbComparisons() {
		return _nbComparisons;
	}
	
	public void incrNbComparisons() {
		_nbComparisons++;
	}
	
	public void resetNbComparisons() {
		_nbComparisons = 0;
	}

	/**
	 * retourne les occurences d'un brin dans le genome
	 * 
	 * @param genome
	 *            le genome
	 * @param strand
	 *            le brin
	 * @return les occurences du brin
	 */
	public abstract StrandOccurences findRepetitiveStrand(Genome genome,
			Strand strand);

	/**
	 * recherche les occurences d'une liste de brins dans le genome
	 * 
	 * @param strands
	 *            la liste des brins
	 * @return la liste des occurences
	 */
	public List<StrandOccurences> findRepetitiveStrands(Genome genome,
			List<Strand> strands) {
		final List<StrandOccurences> strandsOccurences = new LinkedList<StrandOccurences>();
		final Iterator<Strand> strandIterator = strands.iterator();
		while (strandIterator.hasNext()) {
			final StrandOccurences strandOccurences = findRepetitiveStrand(
					genome, strandIterator.next());
			strandsOccurences.add(strandOccurences);
		}
		return strandsOccurences;
	}

}
