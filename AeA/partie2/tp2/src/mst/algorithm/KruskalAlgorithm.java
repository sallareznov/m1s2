package mst.algorithm;

import mst.bean.Edge;
import mst.bean.WeightedGraph;
import mst.sort.EdgesComparator;
import mst.sort.GraphSorter;
import mst.util.CycleDetector;

public class KruskalAlgorithm extends AbstractMinimumSpanningTreeFinder {

	private GraphSorter graphSorter;

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
		final CycleDetector cycleManager = new CycleDetector();
		for (final Edge edge : sortedCopy.getEdges()) {
			mst.addEdge(edge);
			if (cycleManager.hasACycle(mst, edge.getVertex1()))
				mst.removeEdge(edge);
		}
		setExecutionTime(System.currentTimeMillis() - beginning);
		//System.out.println("Kruskal : " + mst.getTotalWeight());
		return mst;
	}
	
	@Override
	public String toString() {
		return "Kruskal algorithm";
	}

}
