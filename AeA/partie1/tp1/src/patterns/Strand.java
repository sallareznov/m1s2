package patterns;

import bases.Base;


public interface Strand {
	
	/**
	 * @return le contenu du brin
	 */
	Base[] getContent();
	
	/**
	 * retourne la base à l'indice <i>i</i>
	 * <i>i</i> est compris entre 0 et la taille du strand
	 * @param index l'indice de la base
	 * @return la base a l'indice <i>i</i>
	 */
	Base getBaseAt(int index);

	/**
	 * @return la taille du brin
	 */
	int getSize();
	
	/**
	 * @return le brin reverse
	 */
	Strand getReverse();
	
	/**
	 * @return le brin complementaire
	 */
	Strand getComplementary();
	
	/**
	 * @return le brin reverse-complementaire
	 */
	Strand getReverseComplementary();
	
	/**
	 * @param size la taille du brin préfixe recherché
	 * @return le brin préfixe
	 */
	Strand getPrefix(int size);
	
	/**
	 * @param size la taille du brin suffixe recherché
	 * @return le brin suffixe
	 */
	Strand getSuffix(int size);
	
	/**
	 * @return le plus long bord du brin 
	 */
	Strand getLongestEdge();
	
	/**
	 * @param prefix le brin teste en tant que prefix
	 * @return true si le brin est prefixe, faux sinon
	 */
	boolean isPrefix(Strand prefix);
	
	/**
	 * @param suffix le brin teste en tant que suffixe
	 * @return true si le brin est suffixe, faux sinon
	 */
	boolean isSuffix(Strand suffix);
	
	/**
	 * Un brin est un bord d'un autre brin s'il est préfixe et suffixe en meme temps
	 * @param preAndSuffix le brin a testé si c'est un bord
	 * @return true si le brin est un bord, false sinon
	 */
	boolean isEdge(Strand preAndSuffix);
	
	@Override
	String toString();
	
	@Override
	boolean equals(Object o);
	
	Strand clone();

}
