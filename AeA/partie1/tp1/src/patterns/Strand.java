package patterns;

import bases.Base;
import bases.BaseFactory;

/**
 * Classe représentant un brin d'ADN
 */
public class Strand {
	
	private Base[] content;
	
	/**
	 * construit le brin d'ADN
	 * @param content le contenu du brin
	 */
	public Strand(Base[] content) {
		this.content = content;
	}
	
	/**
	 * construit le brin d'ADN
	 * @param word la chaine representant le contenu du brin
	 */
	public Strand(String word) {
		content = new Base[word.length()];
		final BaseFactory baseFactory = new BaseFactory();
		for (int i = 0 ; i < word.length() ; i++) {
			content[i] = baseFactory.createBase(word.charAt(i));
		}
	}
	
	/**
	 * @return le contenu du brin
	 */
	public Base[] getContent() {
		return content;
	}
	
	/**
	 * @return la taille du brin
	 */
	public int getSize() {
		return content.length;
	}
	
	/**
	 * @return le brin reverse
	 */
	public Strand getReverse() {
		final int n = content.length;
		final Base[] reverseContent = new Base[n];
		for (int i = 0 ; i < n ; i++) {
			reverseContent[i] = content[n - i - 1];
		}
		return new Strand(reverseContent);
	}
	
	/**
	 * @return le brin complementaire
	 */
	public Strand getComplementary() {
		final int n = content.length;
		final Base[] complementaryContent = new Base[n];
		for (int i = 0 ; i < n ; i++) {
			complementaryContent[i] = content[i].getComplementary();
		}
		return new Strand(complementaryContent);
	}
	
	/**
	 * @return le brin reverse-complementaire
	 */
	public Strand getReverseComplementary() {
		final Strand reverseSubSequence = getReverse();
		return reverseSubSequence.getComplementary();
	}
	
	@Override
	public String toString() {
		final StringBuilder stringBuilder = new StringBuilder();
		for (Base base : content) {
			stringBuilder.append(base.getWording());
		}
		return stringBuilder.toString();
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof Strand) {
			final Strand strand = (Strand) o;
			return (this.toString().equals(strand.toString()));
		}
		return false;
	}

}
