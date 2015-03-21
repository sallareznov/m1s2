package mst.sort;

import java.util.Collections;
import java.util.Comparator;

import mst.bean.Edge;
import mst.bean.WeightedGraph;

public class GraphSorter {
	
	private Comparator<Edge> comparator;
	
	public GraphSorter() {
		comparator = null;
	}
	
	public void setComparator(Comparator<Edge> comparator) {
		this.comparator = comparator;
	}
	
	public WeightedGraph sort(WeightedGraph graph) throws CloneNotSupportedException {
		final WeightedGraph sortedCopy = (WeightedGraph) graph.clone();
		Collections.sort(sortedCopy.getEdges(), comparator);
		return sortedCopy;
	}
	
}
