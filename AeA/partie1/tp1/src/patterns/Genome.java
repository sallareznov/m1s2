package patterns;

import bases.Base;
import bases.BaseFactory;

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
	 * @param motif le motif
	 * @param alphabet
	 */
	public Genome(String motif, Alphabet alphabet) {
		this.alphabet = alphabet;
		this.size = motif.length();
		this.bases = new Base[this.size];
		final BaseFactory baseFactory = new BaseFactory();
		if (this.alphabet.acceptWord(motif))
			this.bases = baseFactory.createBases(motif);
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
