package motif;

public class Motif
{
	/**
	 * L'alphabet dont dépend le motif.
	 * Le motif ne peut contenir que des mots de l'alphabet
	 */
	protected Alphabet alphabet;
	
	/**
	 * Les mots qui composent le motif.
	 */
	protected char[] words;
	
	/**
	 * La taille du mot
	 */
	protected int size;
	
	public Motif(String motif, Alphabet alphabet)
	{
		this.alphabet = alphabet;
		this.size = motif.length();
		this.words = new char[this.size];
		
		for (int i = 0; i < this.size; i++)
		{
			char c = motif.charAt(i);
			if (this.alphabet.containsWord(c))
				this.words[i] = c;
			else
				throw new IllegalArgumentException("String contains invalid letters for the alphabet");
		}
	}
}
