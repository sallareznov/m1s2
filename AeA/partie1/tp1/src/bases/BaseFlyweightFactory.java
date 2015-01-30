package bases;

import java.util.HashMap;
import java.util.Map;

import patterns.Alphabet;


/**
 * Factory servant a creer les bases
 */
public class BaseFlyweightFactory {
	
	private Map<Character, Base> existingBases;
	private Alphabet alphabet;
	
	/**
	 * construit une fabrique de bases
	 * @param alphabet l'alphabet
	 */
	public BaseFlyweightFactory(Alphabet alphabet) {
		existingBases = new HashMap<Character, Base>();
		this.alphabet = alphabet;
	}
	
	/**
	 * creates a base 
	 * @param letter
	 * @return la base creee
	 * @exception IllegalArgumentException si la base est incorrecte
	 */
	public Base createBase(char letter) {
		if (existingBases.get(letter) == null) {
			switch (letter) {
				case 'A' : {
					if (alphabet.containsLetter('T')) {
						existingBases.put('A', new Base('A', 'T')); 
					}
					else {
						existingBases.put('A', new Base('A', 'U'));
					}
					break;
				}
				case 'C' : existingBases.put('C', new Base('C', 'G')); break;
				case 'G' : existingBases.put('G', new Base('G', 'C')); break;
				case 'T' : existingBases.put('T', new Base('T', 'A')); break;
				case 'U' : existingBases.put('U', new Base('U', 'A')); break;
				default : existingBases.put(letter, new Base(letter, letter)); break;
			}
		}
		return existingBases.get(letter);
	}
	
	/**
	 * cree les bases correspondant aux caracteres du mot passe en parametre
	 * @param word le mot
	 * @return les bases
	 */
	public Base[] createBases(String word) {
		final Base[] bases = new Base[word.length()];
		for (int i = 0 ; i < bases.length ; i++) {
			bases[i] = createBase(word.charAt(i));
		}
		return bases;
	}
	
	/**
	 * crée une base A
	 * @return une base A
	 */
	public Base createABase() {
		return createBase('A');
	}
	
	/**
	 * crée une base C
	 * @return une base C
	 */
	public Base createCBase() {
		return createBase('C');
	}
	
	/**
	 * crée une base G
	 * @return une base G
	 */
	public Base createGBase() {
		return createBase('G');
	}
	
	/**
	 * crée une base T
	 * @return une base T
	 */
	public Base createTBase() {
		return createBase('T');
	}
	
}
