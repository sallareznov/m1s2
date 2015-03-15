package mst;

public class PrimAlgorithm extends MSTFinder {

	@Override
	public MST findMST(WeightedGraph graph) {
		final MST mst = new MST();
		final VertexMarkingManager markingManager = new VertexMarkingManager(graph.getSize());
		final NeighborsManager neighborsManager = new NeighborsManager();
		markingManager.mark(0);
		neighborsManager.initNeighbors(graph);
		return null;
	}

}
