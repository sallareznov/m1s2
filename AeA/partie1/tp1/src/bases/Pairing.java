package bases;

public class Pairing {
	
	private char firstBase;
	private char secondBase;
	private boolean perfect;
	
	public Pairing(char firstBase, char secondBase, boolean perfect) {
		this.firstBase = firstBase;
		this.secondBase = secondBase;
		this.perfect = perfect;
	}
	
	public char getFirstBase() {
		return firstBase;
	}
	
	public char getSecondBase() {
		return secondBase;
	}
	
	public boolean isPerfect() {
		return perfect;
	}
	
	public boolean contains(char base) {
		return ((base == firstBase) || (base == secondBase));
	}
	
	@Override
	public String toString() {
		return "{" + firstBase + ", " + secondBase + "}";
	}

}
