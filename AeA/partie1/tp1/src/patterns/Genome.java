package patterns;

import bases.Base;
import bases.BaseFlyweightFactory;

/**
 * Classe representant un genome
 */
public class Genome {
	
	private Alphabet alphabet;
	private Base[] bases;
	private int size;

	/**
	 * construit un genome
	 * @param sequenceString le motif
	 * @param alphabet l'alphabet
	 */
	public Genome(String sequenceString, Alphabet alphabet) {
		this.alphabet = alphabet;
		this.size = sequenceString.length();
		final BaseFlyweightFactory baseFactory = new BaseFlyweightFactory();
		if (this.alphabet.acceptWord(sequenceString))
			this.bases = baseFactory.createBases(sequenceString);
		else
			throw new IllegalArgumentException(
					"String contains invalid letters for the alphabet");
	}
	
	/**
	 * construit un genome
	 * @param bases les bases du genome
	 * @param alphabet l'alphabet
	 */
	public Genome(Base[] bases, Alphabet alphabet) {
		this.bases = bases;
		this.alphabet = alphabet;
	}
	
	/**
	 * @return les bases du genome
	 */
	public Base[] getBases() {
		return bases;
	}
	
	/**
	 * @return la taille du genome
	 */
	public int getSize() {
		return size;
	}
	
	/**
	 * @return l'alphabet des bases du genome
	 */
	public Alphabet getAlphabet() {
		return alphabet;
	}

}
