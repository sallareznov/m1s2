package coloration.util;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import coloration.bean.Edge;
import coloration.bean.Vertex;
import coloration.bean.WeightedGraph;

public class NeighborsManager {

	private Map<Vertex, List<VertexNeighbor>> vertexesToNeighbors;

	public NeighborsManager() {
		vertexesToNeighbors = new HashMap<Vertex, List<VertexNeighbor>>();
	}
	
	public Map<Vertex, List<VertexNeighbor>> getVertexesToNeighbors() {
		return vertexesToNeighbors;
	}

	public void addNeighbors(Vertex vertex1, Vertex vertex2, int weight) {
		List<VertexNeighbor> vertex1Neighbors = vertexesToNeighbors.get(vertex1);
		if (vertex1Neighbors == null) {
			vertex1Neighbors = new LinkedList<VertexNeighbor>();
		}
		vertex1Neighbors.add(new VertexNeighbor(vertex2, weight));
		vertexesToNeighbors.put(vertex1, vertex1Neighbors);
	}

	public List<VertexNeighbor> getNeighbors(Vertex vertex) {
		final List<VertexNeighbor> neighbors = vertexesToNeighbors.get(vertex);
		if (neighbors == null) {
			return new LinkedList<VertexNeighbor>();
		}
		return neighbors;
	}

	public void addNeighbors(Edge edge) {
		addNeighbors(edge.getVertex1(), edge.getVertex2(), edge.getWeight());
		addNeighbors(edge.getVertex2(), edge.getVertex1(), edge.getWeight());
	}

	public void initNeighbors(WeightedGraph graph) {
		vertexesToNeighbors.clear();
		final LinkedList<Edge> graphEdges = new LinkedList<Edge>();
		graphEdges.addAll(graph.getEdges());
		while (!graphEdges.isEmpty()) {
			final Edge poll = graphEdges.poll();
			addNeighbors(poll);
		}
	}

}