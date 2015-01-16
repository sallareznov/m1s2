package motifs;

/**
 * Factory servant à créer les bases
 */
public class BaseFactory {
	
	/**
	 * crée une base A
	 * @return une base A
	 */
	public Base createABase() {
		return new Base('A', 'T');
	}
	
	/**
	 * crée une base C
	 * @return une base C
	 */
	public Base createCBase() {
		return new Base('C', 'G');
	}
	
	/**
	 * crée une base G
	 * @return une base G
	 */
	public Base createGBase() {
		return new Base('G', 'C');
	}
	
	/**
	 * crée une base T
	 * @return une base T
	 */
	public Base createTBase() {
		return new Base('T', 'A');
	}
	
}
