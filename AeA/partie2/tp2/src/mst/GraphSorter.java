package mst;

import java.util.Collections;
import java.util.Comparator;

public class GraphSorter {
	
	private Comparator<Ridge> comparator;
	
	public GraphSorter() {
		comparator = null;
	}
	
	public void setComparator(Comparator<Ridge> comparator) {
		this.comparator = comparator;
	}
	
	public WeightedGraph sort(WeightedGraph graph) throws CloneNotSupportedException {
		final WeightedGraph sortedCopy = (WeightedGraph) graph.clone();
		Collections.sort(sortedCopy.getRidges(), comparator);
		return sortedCopy;
	}
	
}
