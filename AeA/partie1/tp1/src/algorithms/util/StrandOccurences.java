package algorithms.util;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Classe representant les occurences d'un brin
 */
public class StrandOccurences implements Iterable<Integer> {

	private List<Integer> occurences;

	public StrandOccurences() {
		occurences = new LinkedList<Integer>();
	}

	/**
	 * @return la liste d'occurences
	 */
	public List<Integer> getOccurences() {
		return occurences;
	}
	
	/**
	 * @return le nombre d'occurences
	 */
	public int getNbOccurences() {
		return occurences.size();
	}

	/**
	 * ajoute une occurence a la liste
	 * 
	 * @param occurence
	 *            l'occurence a ajouter
	 */
	public boolean addIndex(int occurence) {
		return occurences.add(occurence);
	}
	
	@Override
	public Iterator<Integer> iterator() {
		return occurences.iterator();
	}

	@Override
	public String toString() {
		return occurences.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof StrandOccurences) {
			final StrandOccurences occurences = (StrandOccurences) obj;
			if (occurences.getNbOccurences() != getNbOccurences()) {
				return false;
			}
			final Iterator<Integer> iter1 = occurences.iterator();
			final Iterator<Integer> iter2 = iterator();
			while (iter1.hasNext()) {
				if (iter1.next() != iter2.next())
					return false;
			}
			return true;
		}
		return false;
	}

}
