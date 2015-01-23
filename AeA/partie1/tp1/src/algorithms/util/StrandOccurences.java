package algorithms.util;

import java.util.LinkedList;
import java.util.List;

public class StrandOccurences {

	private List<Integer> indexes;
	
	public StrandOccurences() {
		indexes = new LinkedList<Integer>();
	}
	
	public List<Integer> getIndexes() {
		return indexes;
	}
	
	public void addIndex(int index) {
		indexes.add(index);
	}
	
	@Override
	public String toString() {
		return indexes.toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof StrandOccurences) {
			final StrandOccurences occurences = (StrandOccurences) obj;
			return indexes.equals(occurences.getIndexes());
		}
		return false;
	}

}
