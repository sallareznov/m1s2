package bases;


/**
 * Factory servant a creer les bases
 */
public class BaseFactory {
	
	/**
	 * creates a base 
	 * @param letter
	 * @return la base creee
	 * @exception IllegalArgumentException si la base est incorrecte
	 */
	public Base createBase(char letter) {
		switch (letter) {
			case 'A' : return new Base('A', 'T');
			case 'C' : return new Base('C', 'G');
			case 'G' : return new Base('G', 'C');
			case 'T' : return new Base('T', 'A');
			default : throw new IllegalArgumentException("Base incorrecte !");
		}
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
