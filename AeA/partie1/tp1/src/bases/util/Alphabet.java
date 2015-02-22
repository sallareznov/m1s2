package bases.util;

import java.util.HashSet;
import java.util.Set;

public class Alphabet {
	
	private Set<Character> bases;
	
	public Alphabet() {
		this.bases = new HashSet<Character>();
	}
	
	public void addBase(char base) {
		bases.add(base);
	}
	
	public boolean contains(char base) {
		for (char c : bases) {
			if (c == base)
				return true;
		}
		return false;
	}
	
	public boolean accept(String word) {
		for (int i = 0 ; i < word.length() ; i++) {
			if (!contains(word.charAt(i)))
				return false;
		}
		return true;
	}
	
	public boolean accept(char[] bases) {
		return accept(new String(bases));
	}

	public int size() {
		return bases.size();
	}

	public Character[] getBases() {
		return (Character[]) bases.toArray(new Character[bases.size()]);
	}

}
