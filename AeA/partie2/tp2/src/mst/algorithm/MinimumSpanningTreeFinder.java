package mst.algorithm;

import mst.bean.WeightedGraph;

public interface MinimumSpanningTreeFinder {
	
	WeightedGraph findTree(WeightedGraph graph) throws CloneNotSupportedException;
	
	long getExecutionTime();
	
}
