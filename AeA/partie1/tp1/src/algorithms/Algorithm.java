package algorithms;

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
	 * @param genome le genome sur lequel la recherche s'effectuera
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
	 * recherche les occurences d'une liste de brins dans le genome
	 * @param strands la liste des brins
	 * @return la liste des occurences
	 */
	public abstract List<StrandOccurences> findRepetitiveWords(List<Strand> strands);

}
