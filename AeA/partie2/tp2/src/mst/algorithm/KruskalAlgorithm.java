package mst.algorithm;

import mst.bean.Edge;
import mst.bean.WeightedGraph;
import mst.manager.CycleManager;
import mst.sort.EdgesComparator;
import mst.sort.GraphSorter;

public class KruskalAlgorithm implements MSTFinder {

	private GraphSorter graphSorter;

	public KruskalAlgorithm() {
		graphSorter = new GraphSorter();
		graphSorter.setComparator(new EdgesComparator());
	}

	@Override
	public WeightedGraph findMST(WeightedGraph graph)
			throws CloneNotSupportedException {
		final WeightedGraph sortedCopy = graphSorter.sort(graph);
		final WeightedGraph mst = new WeightedGraph();
		final CycleManager cycleManager = new CycleManager();
		for (final Edge edge : sortedCopy.getEdges()) {
			mst.addEdge(edge);
			if (cycleManager.hasACycle(mst, edge.getVertex1()))
				mst.removeEdge(edge);
		}
		System.out.println("Kruskal : " + mst.getTotalWeight());
		return mst;
	}
	
	@Override
	public String toString() {
		return "Kruskal algorithm";
	}

}
