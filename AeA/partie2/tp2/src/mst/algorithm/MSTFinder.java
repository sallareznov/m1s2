package mst.algorithm;

import mst.bean.WeightedGraph;

public interface MSTFinder {
	
	WeightedGraph findMST(WeightedGraph graph) throws CloneNotSupportedException;
	
}
