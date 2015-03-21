package mst;

import java.util.Random;

public class PrimAlgorithm implements MSTFinder {
	
	private GraphSorter graphSorter;
	
	public PrimAlgorithm() {
		graphSorter = new GraphSorter();
		graphSorter.setComparator(new RidgeComparator());
	}

	@Override
	public WeightedGraph findMST(WeightedGraph graph) throws CloneNotSupportedException {
		final WeightedGraph mst = new WeightedGraph();
		final NeighborsManager neighborsManager = new NeighborsManager();
		neighborsManager.initNeighbors(graph);
		final Random randomizer = new Random();
		final int graphSize = graph.getSize();
		final int randomVertexIndex = randomizer.nextInt(graphSize);
		final Vertex firstVertex = graph.getVertex(randomVertexIndex);
		mst.addVertex(firstVertex);
		while (mst.getSize() < graphSize) {
			final Ridge weakerOutgoingRidge = neighborsManager.getWeakerOutgoingRidge(mst, graph);
			mst.addRidge(weakerOutgoingRidge);
			neighborsManager.removeNeighbor(weakerOutgoingRidge.getVertex1());
			neighborsManager.removeNeighbor(weakerOutgoingRidge.getVertex2());
		}
		return mst;
	}
	
}
