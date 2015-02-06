package bases;

/**
 * Classe representant une base
 */
public class Base {
	
	private char wording;
	private char complementary;
	
	/**
	 * construit une base
	 * @param wording le caractere de la base
	 * @param complementary son complementaire
	 */
	protected Base(char wording, char complementary) {
		this.wording = wording;
		this.complementary = complementary;
	}
	
	/**
	 * @return le caractere de la base
	 */
	public char getWording() {
		return wording;
	}
	
	/**
	 * @return son complementaire
	 */
	public Base getComplementary() {
		return new Base(complementary, wording);
	}
	
	/**
	 * 
	 * @param complementary
	 */
	public void setComplementary(char complementary) {
		this.complementary = complementary;
	}
	
	@Override
	public String toString() {
		return wording + "";
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Base) {
			final Base base = (Base) obj;
			return (base.getWording() == wording);
		}
		return false;
	}

}
