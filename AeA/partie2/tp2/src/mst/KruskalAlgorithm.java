package mst;

public class KruskalAlgorithm extends MSTFinder {

private GraphSorter graphSorter;
	
	public KruskalAlgorithm() {
		graphSorter = new GraphSorter();
		graphSorter.setComparator(new RidgeComparator());
	}

	@Override
	public WeightedGraph findMST(WeightedGraph graph) throws CloneNotSupportedException {
		final WeightedGraph sortedCopy = graphSorter.sort(graph);
		final WeightedGraph mst = new WeightedGraph();
		final CycleManager cycleManager = new CycleManager();
		for (final Ridge ridge : graph.getRidges()) {
			mst.addRidge(ridge);
			if (!cycleManager.isAcyclic(graph, ridge.getVertex1()))
				mst.removeRidge(ridge);
		}
		return mst;
	}

}
