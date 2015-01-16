package motifs;

public class Base {
	
	private char wording;
	private char complementary;
	
	public Base(char wording, char complementary) {
		this.wording = wording;
		this.complementary = complementary;
	}
	
	public char getWording() {
		return wording;
	}
	
	public Base getComplementary() {
		return new Base(complementary, wording);
	}

}
