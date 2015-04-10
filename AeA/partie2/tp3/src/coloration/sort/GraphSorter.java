package coloration.sort;

import java.util.LinkedList;
import java.util.List;

import coloration.bean.Vertex;
import coloration.bean.WeightedGraph;
import coloration.neighbor.NeighborsManager;

public class GraphSorter {
	
	public List<Vertex> sort(WeightedGraph graph, NeighborsManager neighborsManager) throws CloneNotSupportedException {
		final List<Vertex> vertices = new LinkedList<Vertex>();
		final MinHeap<Vertex> heap = new MinHeap<Vertex>(graph.getSize(), new VerticesComparator(neighborsManager));
		for (final Vertex vertex : graph.getVertices()) {
			heap.add(vertex);
		}
		while (!heap.isEmpty()) {
			final Vertex topVertex = heap.remove();
			vertices.add(topVertex);
		}
		return vertices;
	}
	
}
