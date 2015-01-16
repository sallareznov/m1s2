/**
 * Un alphabet est un ensemble fini de lettres
 */
package motifs;

/**
 * @author leo
 *
 */
public class Alphabet
{
	/**
	 * Les mots de l'alphabet
	 */
	protected char[] letters;

	/**
	 * La taille de l'alaphabets
	 */
	protected int size;

	/**
	 * 
	 */
	public Alphabet(char[] letters)
	{
		this.letters = letters;
		this.size = letters.length;
	}

	public char[] getLetters()
	{
		return this.letters;
	}

	public int getSize()
	{
		return this.size;
	}

	public void setSize(int size)
	{
		this.size = size;
	}

	/**
	 * Vérifie que le mot est bien présent dans l'alphabet
	 * @param c le mot a rechercher
	 * @return true si le mot est bien présent, faux sinon
	 */
	public boolean containsWord(char c)
	{
		for (int i = 0; i < this.size; i++)
		{
			if (this.letters[i] == c)
				return true;
		}
		return false;
	}
}
