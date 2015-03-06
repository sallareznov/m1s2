package algorithms;

import java.util.LinkedList;
import java.util.List;

import pattern.Genome;
import pattern.Strand;
import algorithms.util.StrandOccurences;
import bases.Alphabet;

/**
 * Classe abstraite representant un algorithme de recherche
 */
public abstract class Algorithm {

	private int nbComparisons;

	/**
	 * Construit un algorithme (constructeur par defaut)
	 */
	public Algorithm() {
		nbComparisons = 0;
	}

	/**
	 * @return le nombre de comparaisons effectuees par l'algorithme
	 */
	public int getNbComparisons() {
		return nbComparisons;
	}

	/**
	 * incremente le nombre de comparaisons
	 */
	public void incrNbComparisons() {
		nbComparisons++;
	}

	/**
	 * remet a zero le nombre de comparaisons
	 */
	public void resetNbComparisons() {
		nbComparisons = 0;
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
			Strand strand, Alphabet alphabet);

	/**
	 * recherche les occurences d'une liste de brins dans le genome
	 * 
	 * @param strands
	 *            la liste des brins
	 * @return la liste des occurences
	 */
	public List<StrandOccurences> findRepetitiveStrands(Genome genome,
			List<Strand> strands, Alphabet alphabet) {
		final List<StrandOccurences> strandsOccurences = new LinkedList<StrandOccurences>();
		for (final Strand strand : strands) {
			final StrandOccurences strandOccurences = findRepetitiveStrand(
					genome, strand, alphabet);
			strandsOccurences.add(strandOccurences);
		}
		return strandsOccurences;
	}
	
}
