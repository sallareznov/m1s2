package mst;

public class PrimAlgorithm extends MSTFinder {
	
	private GraphSorter graphSorter;
	
	public PrimAlgorithm() {
		graphSorter = new GraphSorter();
		graphSorter.setComparator(new RidgeComparator());
	}

	@Override
	public WeightedGraph findMST(WeightedGraph graph) throws CloneNotSupportedException {
		final WeightedGraph sortedCopy = graphSorter.sort(graph);
//		final MST mst = new MST();
//		final VertexMarkingManager markingManager = new VertexMarkingManager(graph.getSize());
//		final NeighborsManager neighborsManager = new NeighborsManager();
//		markingManager.mark(0);
//		neighborsManager.initNeighbors(graph);
//		return null;
		return null;
	}

}
