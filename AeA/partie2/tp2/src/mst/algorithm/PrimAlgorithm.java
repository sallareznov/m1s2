package mst.algorithm;

import java.util.Random;

import mst.bean.Edge;
import mst.bean.Vertex;
import mst.bean.WeightedGraph;
import mst.manager.NeighborsManager;
import mst.sort.EdgesComparator;
import mst.sort.GraphSorter;

public class PrimAlgorithm implements MSTFinder {
	
	private GraphSorter graphSorter;
	
	public PrimAlgorithm() {
		graphSorter = new GraphSorter();
		graphSorter.setComparator(new EdgesComparator());
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
		Vertex lastAddedVertex = firstVertex;
		while (mst.getSize() < graphSize) {
			final Edge weakerOutgoingEdge = neighborsManager.getWeakerOutgoingEdge(mst, graph, lastAddedVertex);
			if (weakerOutgoingEdge == null) {
				return mst;
			}
			if (!lastAddedVertex.equals(weakerOutgoingEdge.getVertex1()))
				lastAddedVertex = weakerOutgoingEdge.getVertex1();
			else
				lastAddedVertex = weakerOutgoingEdge.getVertex2();
			mst.addEdge(weakerOutgoingEdge);
			neighborsManager.removeNeighbors(mst, lastAddedVertex);
		}
		System.out.println("Prim : " + mst.getTotalWeight());
		return mst;
	}
	
	@Override
	public String toString() {
		return "Prim algorithm";
	}
	
}
