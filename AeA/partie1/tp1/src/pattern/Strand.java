package pattern;

import bases.PairingsManager;
import bases.util.NonExistentPairingException;

/**
 * Classe représentant un brin d'ADN
 */
public class Strand {

	private PairingsManager pairingsManager;
	private Character[] content;

	/**
	 * construit le brin d'ADN
	 * 
	 * @param content
	 *            le contenu du brin
	 */
	public Strand(Character[] content, PairingsManager pairingsManager) {
		this.setContent(content);
		this.setManager(pairingsManager);
	}

	/**
	 * construit le brin d'ADN
	 * 
	 * @param word
	 *            la chaine representant le contenu du brin
	 */
	public Strand(String word, PairingsManager pairingsManager) {
		this.setContent(word);
		this.setManager(pairingsManager);
	}

	/**
	 * @return le contenu du brin
	 */
	public Character[] getContent() {
		return this.content;
	}

	public void setContent(Character[] content) {
		this.content = content;
	}

	public void setContent(String word) {
		this.content = new Character[word.length()];
		for (int i = 0; i < this.content.length; i++) {
			this.content[i] = word.charAt(i);
		}
	}

	/**
	 * @return le manager des paires
	 */
	public PairingsManager getManager() {
		return this.pairingsManager;
	}

	public void setManager(PairingsManager pairingsManager) {
		this.pairingsManager = pairingsManager;
	}

	/**
	 * @return la taille du brin
	 */
	public int getSize() {
		return this.getContent().length;
	}

	public Strand getReverse() {
		final int n = this.getSize();
		final Character[] reverseContent = new Character[n];
		for (int i = n - 1; i >= 0; i--) {
			reverseContent[n - i - 1] = content[i];
		}
		return new Strand(reverseContent, this.getManager());
	}

	public Strand getComplementary() throws NonExistentPairingException {
		final int n = this.getSize();
		final Character[] complementaryContent = new Character[n];
		for (int i = 0; i < n; i++) {
			complementaryContent[i] = this.getManager().getComplementaryOf(
					this.getBaseAt(i));
		}
		return new Strand(complementaryContent, this.getManager());
	}

	public Strand getReverseComplementary() throws NonExistentPairingException {
		final Strand reverseSubSequence = getReverse();
		return reverseSubSequence.getComplementary();
	}

	public Strand getPrefix(int size) {
		if (size <= 0) {
			return new Strand(new Character[0], pairingsManager);
		}
		final Character[] prefixBases = new Character[size];
		for (int i = 0; i < prefixBases.length; i++) {
			prefixBases[i] = this.getBaseAt(i);
		}
		return new Strand(prefixBases, pairingsManager);
	}

	public Strand getSuffix(int size) {
		if (size <= 0) {
			return new Strand(new Character[0], pairingsManager);
		}
		final Character[] suffixBases = new Character[size];
		for (int i = this.getSize() - size; i < this.getSize(); i++) {
			suffixBases[i - (this.getSize() - size)] = this.getBaseAt(i);
		}
		return new Strand(suffixBases, this.getManager());
	}

	public Strand getLongestEdge() {
		Strand edge = new Strand(new Character[0], pairingsManager);
		for (int i = 0; i < this.getSize() - 1; i++) {
			final Strand prefix = this.getPrefix(i);
			final Strand suffix = this.getSuffix(i);
			if (prefix.equals(suffix)) {
				edge = prefix;
			}
		}
		return edge;
	}

	public boolean isPrefix(Strand prefix) {
		if (prefix.getSize() == 0) {
			return false;
		}
		for (int i = 0; i < prefix.getSize(); i++) {
			if (!(prefix.getBaseAt(i) == (this.getBaseAt(i))))
				return false;
		}
		return true;
	}

	public boolean isSuffix(Strand suffix) {
		if (suffix.getSize() == 0) {
			return false;
		}
		for (int i = suffix.getSize() - 1; i >= 0; i--) {
			int index = i + (this.getSize() - suffix.getSize());
			if (!(getBaseAt(index) == suffix.getBaseAt(i)))
				return false;
		}
		return true;
	}

	public boolean isEdge(Strand preAndSuffix) {
		return this.isPrefix(preAndSuffix) && this.isSuffix(preAndSuffix);
	}

	@Override
	public String toString() {
		final StringBuilder stringBuilder = new StringBuilder();
		for (char base : content) {
			stringBuilder.append(base);
		}
		return stringBuilder.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Strand) {
			final Strand str = (Strand) obj;
			if (this.getSize() != str.getSize()) {
				return false;
			}
			for (int i = 0 ; i < content.length ; i++) {
				if (str.getBaseAt(i) != content[i])
					return false;
			}
			return true;
		}
		return false;
	}

	public char getBaseAt(int index) {
		return this.getContent()[index];
	}

	public Strand clone() {
		final Character[] contentCopy = new Character[content.length];
		System.arraycopy(content, 0, contentCopy, 0, content.length);
		return new Strand(contentCopy, this.getManager());
	}

}
