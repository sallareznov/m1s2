package patterns;

import java.util.Arrays;

import bases.util.PairingsManager;

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
	public Genome(char[] bases, PairingsManager pairingsManager) {
		super(bases, pairingsManager);
	}

	@Override
	public String toString() {
		return super.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Genome) {
			final Genome genome = (Genome) obj;
			return Arrays.equals(this.getContent(), genome.getContent());
		}
		return false;
	}
}
