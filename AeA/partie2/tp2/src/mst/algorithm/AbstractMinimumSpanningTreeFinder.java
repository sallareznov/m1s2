package mst.algorithm;

import mst.bean.WeightedGraph;

public abstract class AbstractMinimumSpanningTreeFinder implements
		MinimumSpanningTreeFinder {

	private long executionTime;

	@Override
	public abstract WeightedGraph findTree(WeightedGraph graph)
			throws CloneNotSupportedException;

	@Override
	public long getExecutionTime() {
		return executionTime;
	}

}
