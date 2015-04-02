package mst.algorithm;

import java.util.Random;

import mst.bean.Edge;
import mst.bean.Vertex;
import mst.bean.WeightedGraph;
import mst.manager.NeighborsManager;

public class PrimAlgorithm implements MinimumSpanningTreeFinder {
	
	private long executionTime; 
	
	@Override
	public WeightedGraph findTree(WeightedGraph graph) throws CloneNotSupportedException {
		final WeightedGraph mst = new WeightedGraph();
		final NeighborsManager neighborsManager = new NeighborsManager();
		neighborsManager.initNeighbors(graph);
		final long beginning = System.currentTimeMillis();
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
			if (mst.getVertexes().contains(weakerOutgoingEdge.getVertex1()))
				lastAddedVertex = weakerOutgoingEdge.getVertex2();
			else
				lastAddedVertex = weakerOutgoingEdge.getVertex1();
			mst.addEdge(weakerOutgoingEdge);
		}
		executionTime = System.currentTimeMillis() - beginning;
		return mst;
	}
	
	public long getExecutionTime() {
		return executionTime;
	}
	
	@Override
	public String toString() {
		return "Prim algorithm";
	}
	
}
