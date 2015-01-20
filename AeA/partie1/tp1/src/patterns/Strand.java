package patterns;

import bases.Base;
import bases.BaseFlyweightFactory;

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
		final BaseFlyweightFactory baseFactory = new BaseFlyweightFactory();
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
	
	/**
	 * @param size la taille du brin préfixe recherché
	 * @return le brin préfixe
	 */
	public Strand getPrefix(int size)
	{
		Base[] prefixBases = new Base[size];
		for (int i = 0; i < prefixBases.length; i++)
		{
			prefixBases[i] = this.getContent()[i];
		}
		return new Strand(prefixBases);
	}
	
	/**
	 * @param size la taille du brin suffixe recherché
	 * @return le brin suffixe
	 */
	public Strand getSuffix(int size)
	{
		Base[] prefixBases = new Base[size];
		for (int i = prefixBases.length - 1; i <= 0; i--)
		{
			prefixBases[i] = this.getContent()[i];
		}
		return new Strand(prefixBases);
	}
	
	/**
	 * @param prefix le brin teste en tant que prefix
	 * @return true si le brin est prefixe, faux sinon
	 */
	public Boolean isPrefix(Strand prefix)
	{
		for (int i = 0; i < prefix.getSize(); i++)
		{
			if (!prefix.getContent()[i].equals(this.getContent()[i]))
				return false;
		}
		return true;
	}
	
	/**
	 * @param suffix le brin teste en tant que suffixe
	 * @return true si le brin est suffixe, faux sinon
	 */
	public Boolean isSuffix(Strand suffix)
	{
		for (int i = 0; i < suffix.getSize(); i++)
		{
			int baseIndexSuf = this.getSize() - i - 1;
			if (!suffix.getContent()[i].equals(this.getContent()[baseIndexSuf]))
				return false;
		}
		return true;
	}
	
	/**
	 * Un brin est un bord d'un autre brin s'il est préfixe et suffixe en meme temps
	 * @param preAndSuffix le brin a testé si c'est un bord
	 * @return true si le brin est un bord, false sinon
	 */
	public Boolean isEdge(Strand preAndSuffix)
	{
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
	public boolean equals(Object o) {
		if (o instanceof Strand) {
			final Strand strand = (Strand) o;
			return (this.toString().equals(strand.toString()));
		}
		return false;
	}

}
