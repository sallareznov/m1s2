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

	private Genome genome;

	/**
	 * construit un algorithme de recherche
	 * 
	 * @param genome
	 *            le genome sur lequel la recherche s'effectuera
	 */
	public Algorithm(Genome genome) {
		this.genome = genome;
	}

	/**
	 * @return le genome
	 */
	public Genome getGenome() {
		return genome;
	}
	
	/**
	 * retourne les occurences d'un brin dans le genome
	 * @param strand le brin
	 * @param genome le genome
	 * @return les occurences du brin
	 */
	public abstract StrandOccurences findRepetitiveStrand(Strand strand);

	/**
	 * recherche les occurences d'une liste de brins dans le genome
	 * 
	 * @param strands
	 *            la liste des brins
	 * @return la liste des occurences
	 */
	public List<StrandOccurences> findRepetitiveStrands(List<Strand> strands) {
		final List<StrandOccurences> strandsOccurences = new LinkedList<StrandOccurences>();
		final Iterator<Strand> strandIterator = strands.iterator();
		while (strandIterator.hasNext()) {
			final StrandOccurences strandOccurences = findRepetitiveStrand(
					strandIterator.next());
			strandsOccurences.add(strandOccurences);
		}
		return strandsOccurences;
	}

}
