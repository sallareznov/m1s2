package patterns;

import bases.util.PairingsManager;

/**
 * Classe representant un genome
 */
public class Genome extends Strand
{
	/**
	 * construit un genome
	 * 
	 * @param sequenceString
	 *            le motif
	 * @param alphabet
	 *            l'alphabet
	 */
	public Genome(String sequenceString, PairingsManager manager)
	{
		super(sequenceString, manager);
	}

	/**
	 * construit un genome
	 * 
	 * @param bases
	 *            les bases du genome
	 * @param alphabet
	 *            l'alphabet
	 */
	public Genome(char[] bases, PairingsManager manager)
	{
		super(bases, manager);
	}
}
