package patterns;

/**
 * @author leo
 * Un alphabet est un ensemble fini de lettres
 */
public class Alphabet {

	private Character[] letters;
	private int size;

	/**
	 * construit l'alphabet
	 * @param letters les lettres qui composent l'alphabet
	 */
	public Alphabet(Character[] letters) {
		this.letters = letters;
		this.size = letters.length;
	}

	/**
	 * @return les lettres de l'alphabet
	 */
	public Character[] getLetters() {
		return this.letters;
	}

	/**
	 * @return la taille de l'alphabet
	 */
	public int getSize() {
		return this.size;
	}

	/**
	 * Vérifie que le mot est bien présent dans l'alphabet
	 * 
	 * @param c
	 *            le mot a rechercher
	 * @return true si le mot est bien présent, faux sinon
	 */
	public boolean containsLetter(char c) {
		for (int i = 0; i < this.size; i++) {
			if (this.letters[i] == c)
				return true;
		}
		return false;
	}
	
	/**
	 * teste si l'alphabet accepte le mot passse en parametre
	 * @param word le mot a tester
	 * @return <code>true</code> si l'alphabet accepte le mot, <code>false</code> sinon
	 */
	public boolean acceptWord(String word) {
		for (int i = 0 ; i < this.size ; i++) {
			if (!containsLetter(word.charAt(i)))
				return false;
		}
		return true;
	}
}
