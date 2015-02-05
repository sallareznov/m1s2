package algorithms.util;

import java.util.LinkedList;
import java.util.List;

/**
 * Classe representant les occurences d'un brin
 */
public class StrandOccurences {

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
     * ajoute une occurence a la liste
     * @param occurence l'occurence a ajouter
     */
    public boolean addIndex(int occurence) {
	return occurences.add(occurence);
    }

    @Override
    public String toString() {
	return occurences.toString();
    }

    @Override
    public boolean equals(Object obj) {
	if (obj instanceof StrandOccurences) {
	    final StrandOccurences occurences = (StrandOccurences) obj;
	    return occurences.equals(occurences.getOccurences());
	}
	return false;
    }

}
