package motifs;

public class Base {
	
	private char wording;
	private char complementary;
	
	/**
	 * construit une base
	 * @param wording le caractère de la base
	 * @param complementary son complémentaire
	 */
	public Base(char wording, char complementary) {
		this.wording = wording;
		this.complementary = complementary;
	}
	
	/**
	 * @return le caractère de la base
	 */
	public char getWording() {
		return wording;
	}
	
	/**
	 * @return son complémentaire
	 */
	public Base getComplementary() {
		return new Base(complementary, wording);
	}

}
