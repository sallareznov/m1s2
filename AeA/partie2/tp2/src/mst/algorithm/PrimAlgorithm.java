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
		while (mst.getSize() < graphSize) {
			final Edge weakerOutgoingEdge = neighborsManager.getWeakerOutgoingEdge(mst, graph);
			if (weakerOutgoingEdge == null) {
				return mst;
			}
			mst.addEdge(weakerOutgoingEdge);
			neighborsManager.removeNeighbor(weakerOutgoingEdge.getVertex1());
			neighborsManager.removeNeighbor(weakerOutgoingEdge.getVertex2());
		}
		return mst;
	}
	
	@Override
	public String toString() {
		return "Prim algorithm";
	}
	
}
