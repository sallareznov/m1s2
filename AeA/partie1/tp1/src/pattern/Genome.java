package pattern;

import bases.PairingsManager;

/**
 * Classe representant un genome
 */
public class Genome extends Strand {
	/**
	 * construit un genome
	 * 
	 * @param sequenceString
	 *            le motif
	 * @param alphabet
	 *            l'alphabet
	 */
	public Genome(String sequenceString, PairingsManager pairingsManager) {
		super(sequenceString, pairingsManager);
	}

	/**
	 * construit un genome
	 * 
	 * @param bases
	 *            les bases du genome
	 * @param alphabet
	 *            l'alphabet
	 */
	public Genome(Character[] bases, PairingsManager pairingsManager) {
		super(bases, pairingsManager);
	}

}
