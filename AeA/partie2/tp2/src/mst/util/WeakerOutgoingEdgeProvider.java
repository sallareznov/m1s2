package mst.util;

import mst.bean.Edge;
import mst.bean.Vertex;
import mst.bean.WeightedGraph;
import mst.sort.EdgesComparator;
import mst.sort.MinHeap;

public class WeakerOutgoingEdgeProvider {
	
	private NeighborsManager neighborsManager;
	private MinHeap<Edge> minHeap;
	
	public WeakerOutgoingEdgeProvider(NeighborsManager neighborsManager, int nbEdges) {
		this.neighborsManager = neighborsManager;
		minHeap = new MinHeap<Edge>(nbEdges, new EdgesComparator());
	}
	
	public Edge getWeakerOutgoingEdge(WeightedGraph subGraph,
			WeightedGraph graph, Vertex lastAddedVertex) {
		for (final VertexNeighbor neighbor : neighborsManager.getNeighbors(lastAddedVertex)) {
			final Edge edge = graph.getEdge(lastAddedVertex, neighbor.getNeighbor(), neighbor);
			if (!minHeap.contains(edge) && !subGraph.containsVertexes(edge)) {
				minHeap.add(edge);
			}
		}
		Edge weakerEdge = minHeap.remove();
		while (weakerEdge != null && subGraph.containsVertexes(weakerEdge)) {
			weakerEdge =  minHeap.remove();
		}
		return weakerEdge;
	}

}
