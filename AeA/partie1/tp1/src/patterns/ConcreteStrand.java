package patterns;

import java.util.Arrays;

import bases.Base;
import bases.BaseFlyweightFactory;

/**
 * Classe représentant un brin d'ADN
 */
public class ConcreteStrand implements Strand {

	private Base[] content;

	/**
	 * construit le brin d'ADN
	 * 
	 * @param content
	 *            le contenu du brin
	 */
	public ConcreteStrand(Base[] content) {
		this.content = content;
	}

	/**
	 * construit le brin d'ADN
	 * 
	 * @param word
	 *            la chaine representant le contenu du brin
	 */
	public ConcreteStrand(String word) {
		content = new Base[word.length()];
		final BaseFlyweightFactory baseFactory = new BaseFlyweightFactory();
		for (int i = 0; i < word.length(); i++) {
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

	@Override
	public Strand getReverse() {
		final int n = content.length;
		final Base[] reverseContent = new Base[n];
		for (int i = 0; i < n; i++) {
			reverseContent[i] = content[n - i - 1];
		}
		return new ConcreteStrand(reverseContent);
	}

	@Override
	public Strand getComplementary() {
		final int n = content.length;
		final Base[] complementaryContent = new Base[n];
		for (int i = 0; i < n; i++) {
			complementaryContent[i] = content[i].getComplementary();
		}
		return new ConcreteStrand(complementaryContent);
	}

	@Override
	public Strand getReverseComplementary() {
		final Strand reverseSubSequence = getReverse();
		return reverseSubSequence.getComplementary();
	}

	@Override
	public Strand getPrefix(int size) {
		if (size <= 0) {
			return new EmptyStrand();
		}
		Base[] prefixBases = new Base[size];
		for (int i = 0; i < prefixBases.length; i++) {
			prefixBases[i] = this.getContent()[i];
		}
		return new ConcreteStrand(prefixBases);
	}

	@Override
	public Strand getSuffix(int size) {
		if (size <= 0) {
			return new EmptyStrand();
		}
		Base[] suffixBases = new Base[size];
		for (int i = getSize() - size ; i < getSize() ; i++) {
			suffixBases[i - (getSize() - size)] = content[i];
		}
		return new ConcreteStrand(suffixBases);
	}

	@Override
	public Strand getLongestEdge() {
		Strand edge = new EmptyStrand();
		for (int i = 0; i <= getSize() / 2 ; i++) {
			final Strand prefix = getPrefix(i);
			final Strand suffix = getSuffix(i);
			if (prefix.equals(suffix))
				edge = prefix;
		}
		return edge;
	}

	@Override
	public boolean isPrefix(Strand prefix) {
		if (prefix.getSize() == 0) {
			return false;
		}
		for (int i = 0; i < prefix.getSize(); i++) {
			if (!prefix.getContent()[i].equals(this.getContent()[i]))
				return false;
		}
		return true;
	}

	@Override
	public boolean isSuffix(Strand suffix) {
		if (suffix.getSize() == 0) {
			return false;
		}
		for (int i = suffix.getSize() - 1 ; i >= 0 ; i--) {
			if (!getContent()[i + (getSize() - suffix.getSize())].equals(suffix.getContent()[i]))
				return false;
		}
		return true;
	}

	@Override
	public boolean isEdge(Strand preAndSuffix) {
		return this.isPrefix(preAndSuffix) && this.isSuffix(preAndSuffix);
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
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ConcreteStrand other = (ConcreteStrand) obj;
		if (!Arrays.equals(content, other.content))
			return false;
		return true;
	}

}
