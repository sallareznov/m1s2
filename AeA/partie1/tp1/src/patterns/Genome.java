package patterns;

import bases.Base;
import bases.BaseFlyweightFactory;

/**
 * Classe representant un genome
 */
public class Genome {
	
	/**
	 * L'alphabet dont depend le motif. Le motif ne peut contenir que des mots
	 * de l'alphabet
	 */
	private Alphabet alphabet;

	/**
	 * Les mots qui composent le motif.
	 */
	private Base[] bases;

	/**
	 * La taille du mot
	 */
	private int size;

	/**
	 * construit un genome
	 * @param sequenceString le motif
	 * @param alphabet
	 */
	public Genome(String sequenceString, Alphabet alphabet) {
		this.alphabet = alphabet;
		this.size = sequenceString.length();
		this.bases = new Base[this.size];
		final BaseFlyweightFactory baseFactory = new BaseFlyweightFactory();
		if (this.alphabet.acceptWord(sequenceString))
			this.bases = baseFactory.createBases(sequenceString);
		else
			throw new IllegalArgumentException(
					"String contains invalid letters for the alphabet");
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
