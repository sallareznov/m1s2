package patterns;

import java.util.Arrays;

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
	public Genome(String sequenceString) {
		super(sequenceString);
	}

	/**
	 * construit un genome
	 * 
	 * @param bases
	 *            les bases du genome
	 * @param alphabet
	 *            l'alphabet
	 */
	public Genome(char[] bases) {
		super(bases);
	}

	@Override
	public String toString() {
		return new Strand(this.getContent()).toString();
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
