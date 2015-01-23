package patterns;

import bases.Base;


public interface Strand {
	
	/**
	 * @return le contenu du brin
	 */
	public Base[] getContent();
	
	/**
	 * retourne la base à l'indice <i>i</i>
	 * <i>i</i> est compris entre 0 et la taille du strand
	 * @param index l'indice de la base
	 * @return la base a l'indice <i>i</i>
	 */
	public Base getBaseAt(int index);

	/**
	 * @return la taille du brin
	 */
	public int getSize();
	
	/**
	 * @return le brin reverse
	 */
	public Strand getReverse();
	
	/**
	 * @return le brin complementaire
	 */
	public Strand getComplementary();
	
	/**
	 * @return le brin reverse-complementaire
	 */
	public Strand getReverseComplementary();
	
	/**
	 * @param size la taille du brin préfixe recherché
	 * @return le brin préfixe
	 */
	public Strand getPrefix(int size);
	
	/**
	 * @param size la taille du brin suffixe recherché
	 * @return le brin suffixe
	 */
	public Strand getSuffix(int size);
	
	/**
	 * @return le plus long bord du brin 
	 */
	public Strand getLongestEdge();
	
	/**
	 * @param prefix le brin teste en tant que prefix
	 * @return true si le brin est prefixe, faux sinon
	 */
	public boolean isPrefix(Strand prefix);
	
	/**
	 * @param suffix le brin teste en tant que suffixe
	 * @return true si le brin est suffixe, faux sinon
	 */
	public boolean isSuffix(Strand suffix);
	
	/**
	 * Un brin est un bord d'un autre brin s'il est préfixe et suffixe en meme temps
	 * @param preAndSuffix le brin a testé si c'est un bord
	 * @return true si le brin est un bord, false sinon
	 */
	public boolean isEdge(Strand preAndSuffix);
	
	@Override
	public String toString();
	
	@Override
	public boolean equals(Object o);

}
