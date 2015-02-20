package patterns;

import java.util.Arrays;

import bases.Base;
import bases.BaseFlyweightFactory;
import bases.util.PairingManager;

/**
 * Classe représentant un brin d'ADN
 */
public class Strand
{
	/**
	 * Manager pour gere les paires
	 */
	private PairingManager manager;

	/**
	 * Contenu du brin
	 */
	private char[] content;

	/**
	 * construit le brin d'ADN
	 * 
	 * @param content
	 *            le contenu du brin
	 */
	public Strand(char[] content)
	{
		this.setContent(content);
	}

	/**
	 * construit le brin d'ADN
	 * 
	 * @param word
	 *            la chaine representant le contenu du brin
	 */
	public Strand(String word)
	{
		this.setContent(word);
	}

	/**
	 * @return le contenu du brin
	 */
	public char[] getContent()
	{
		return this.content;
	}

	public void setContent(char[] content)
	{
		this.content = content;
	}

	public void setContent(String word)
	{
		this.content = new char[word.length()];
		for (int i = 0; i < this.content.length; i++)
		{
			this.content[i] = word.charAt(i);
		}
	}

	/**
	 * @return le manager des paires
	 */
	public PairingManager getManager()
	{
		return this.manager;
	}

	/**
	 * @return la taille du brin
	 */
	public int getSize()
	{
		return this.getContent().length;
	}

	public Strand getReverse()
	{
		final int n = this.getSize();
		final char[] reverseContent = new char[n];
		for (int i = n - 1; i >= 0; i--)
		{
			reverseContent[i] = content[i];
		}
		return new Strand(reverseContent);
	}

	public Strand getComplementary()
	{
		final int n = this.getSize();
		final char[] complementaryContent = new char[n];
		for (int i = 0; i < n; i++)
		{
			complementaryContent[i] = this.getManager().getComplementary(
					this.getBaseAt(i));
		}
		return new Strand(complementaryContent);
	}

	public Strand getReverseComplementary()
	{
		final Strand reverseSubSequence = getReverse();
		return reverseSubSequence.getComplementary();
	}

	public Strand getPrefix(int size)
	{
		if (size <= 0) { return new Strand(new char[0]); }
		char[] prefixBases = new char[size];
		for (int i = 0; i < prefixBases.length; i++)
		{
			prefixBases[i] = this.getBaseAt(i);
		}
		return new Strand(prefixBases);
	}

	public Strand getSuffix(int size)
	{
		if (size <= 0) { return new Strand(new char[0]); }
		char[] suffixBases = new char[size];
		for (int i = this.getSize() - size; i < this.getSize(); i++)
		{
			suffixBases[i - (this.getSize() - size)] = this.getBaseAt(i);
		}
		return new Strand(suffixBases);
	}

	public Strand getLongestEdge()
	{
		Strand edge = new Strand(new char[0]);
		for (int i = 0; i <= this.getSize(); i++)
		{
			final Strand prefix = this.getPrefix(i);
			final Strand suffix = this.getSuffix(i);
			if (prefix.equals(suffix)) edge = prefix;
		}
		return edge;
	}

	public boolean isPrefix(Strand prefix)
	{
		if (prefix.getSize() == 0) { return false; }
		for (int i = 0; i < prefix.getSize(); i++)
		{
			if (!(prefix.getBaseAt(i) == (this.getBaseAt(i)))) return false;
		}
		return true;
	}

	public boolean isSuffix(Strand suffix)
	{
		if (suffix.getSize() == 0) { return false; }
		for (int i = suffix.getSize() - 1; i >= 0; i--)
		{
			int index = i + (this.getSize() - suffix.getSize());
			if (!(getBaseAt(index) == suffix.getBaseAt(i))) return false;
		}
		return true;
	}

	public boolean isEdge(Strand preAndSuffix)
	{
		return this.isPrefix(preAndSuffix) && this.isSuffix(preAndSuffix);
	}

	@Override
	public String toString()
	{
		final StringBuilder stringBuilder = new StringBuilder();
		for (char base : content)
		{
			stringBuilder.append(base);
		}
		return stringBuilder.toString();
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Strand other = (Strand) obj;
		if (!Arrays.equals(content, other.content)) return false;
		return true;
	}

	public char getBaseAt(int index)
	{
		return this.getContent()[index];
	}

	public Strand clone()
	{
		final char[] contentCopy = new char[content.length];
		System.arraycopy(content, 0, contentCopy, 0, content.length);
		return new Strand(contentCopy);
	}

}
