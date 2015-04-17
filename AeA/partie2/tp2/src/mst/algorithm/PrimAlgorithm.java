package mst.algorithm;

import java.util.Random;

import mst.bean.Edge;
import mst.bean.Vertex;
import mst.bean.WeightedGraph;
import mst.util.NeighborsManager;
import mst.util.WeakerOutgoingEdgeProvider;

public class PrimAlgorithm extends AbstractMinimumSpanningTreeFinder {

	@Override
	public WeightedGraph findTree(WeightedGraph graph)
			throws CloneNotSupportedException {
		final WeightedGraph mst = new WeightedGraph();
		final NeighborsManager neighborsManager = new NeighborsManager();
		neighborsManager.initNeighbors(graph);
		final WeakerOutgoingEdgeProvider weakerOutgoingEdgeProvider = new WeakerOutgoingEdgeProvider(
				neighborsManager, graph.getEdges().size());
		final long beginning = System.currentTimeMillis();
		final Random randomizer = new Random();
		final int graphSize = graph.getSize();
		final int randomVertexIndex = randomizer.nextInt(graphSize);
		final Vertex firstVertex = graph.getVertex(randomVertexIndex);
		mst.addVertex(firstVertex);
		Vertex lastAddedVertex = firstVertex;
		while (mst.getSize() < graphSize) {
			final Edge weakerOutgoingEdge = weakerOutgoingEdgeProvider
					.getWeakerOutgoingEdge(mst, graph, lastAddedVertex);
			if (weakerOutgoingEdge == null) {
				//System.out.println("Prim : " + mst.getTotalWeight());
				return mst;
			}
			if (mst.getVertices().contains(weakerOutgoingEdge.getVertex1()))
				lastAddedVertex = weakerOutgoingEdge.getVertex2();
			else
				lastAddedVertex = weakerOutgoingEdge.getVertex1();
			mst.addEdge(weakerOutgoingEdge);
		}
		setExecutionTime(System.currentTimeMillis() - beginning);
		//System.out.println("Prim : " + mst.getTotalWeight());
		return mst;
	}

	@Override
	public String toString() {
		return "Prim algorithm";
	}

}
