package mst.algorithm;

import mst.bean.Edge;
import mst.bean.WeightedGraph;
import mst.manager.CycleManager;
import mst.sort.EdgesComparator;
import mst.sort.GraphSorter;

public class KruskalAlgorithm implements MinimumSpanningTreeFinder {

	private GraphSorter graphSorter;
	private long executionTime;

	public KruskalAlgorithm() {
		graphSorter = new GraphSorter();
		graphSorter.setComparator(new EdgesComparator());
	}

	@Override
	public WeightedGraph findTree(WeightedGraph graph)
			throws CloneNotSupportedException {
		final long beginning = System.currentTimeMillis();
		final WeightedGraph sortedCopy = graphSorter.sort(graph);
		final WeightedGraph mst = new WeightedGraph();
		final CycleManager cycleManager = new CycleManager();
		for (final Edge edge : sortedCopy.getEdges()) {
			mst.addEdge(edge);
			if (cycleManager.hasACycle(mst, edge.getVertex1()))
				mst.removeEdge(edge);
		}
		executionTime = System.currentTimeMillis() - beginning;
		return mst;
	}
	
	@Override
	public long getExecutionTime() {
		return executionTime;
	}
	
	@Override
	public String toString() {
		return "Kruskal algorithm";
	}

}
