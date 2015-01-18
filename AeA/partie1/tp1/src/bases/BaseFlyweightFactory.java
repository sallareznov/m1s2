package bases;

import java.util.HashMap;
import java.util.Map;


/**
 * Factory servant a creer les bases
 */
public class BaseFlyweightFactory {
	
	private Map<Character, Base> bases;
	
	/**
	 * construit une fabrique de bases
	 */
	public BaseFlyweightFactory() {
		bases = new HashMap<Character, Base>();
	}
	
	/**
	 * creates a base 
	 * @param letter
	 * @return la base creee
	 * @exception IllegalArgumentException si la base est incorrecte
	 */
	public Base createBase(char letter) {
		if (bases.get(letter) == null) {
			switch (letter) {
				case 'A' : bases.put('A', new Base('A', 'T')); break;
				case 'C' : bases.put('C', new Base('C', 'G')); break;
				case 'G' : bases.put('G', new Base('G', 'C')); break;
				case 'T' : bases.put('T', new Base('T', 'A')); break;
				default : throw new IllegalArgumentException("Base incorrecte !");
			}
		}
		return bases.get(letter);
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
