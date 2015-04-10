package coloration.neighbor;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import coloration.bean.Edge;
import coloration.bean.Vertex;
import coloration.bean.WeightedGraph;

public class NeighborsManager {

	private Map<Vertex, List<Vertex>> verticesToNeighbors;

	public NeighborsManager() {
		verticesToNeighbors = new HashMap<Vertex, List<Vertex>>();
	}

	public void addNeighbors(Vertex vertex1, Vertex vertex2) {
		List<Vertex> vertex1Neighbors = verticesToNeighbors.get(vertex1);
		if (vertex1Neighbors == null) {
			vertex1Neighbors = new LinkedList<Vertex>();
		}
		vertex1Neighbors.add(vertex2);
		verticesToNeighbors.put(vertex1, vertex1Neighbors);
	}

	public List<Vertex> getNeighbors(Vertex vertex) {
		final List<Vertex> neighbors = verticesToNeighbors.get(vertex);
		if (neighbors == null) {
			return new LinkedList<Vertex>();
		}
		return neighbors;
	}

	public void addNeighbors(Edge edge) {
		addNeighbors(edge.getVertex1(), edge.getVertex2());
		addNeighbors(edge.getVertex2(), edge.getVertex1());
	}

	public void initNeighbors(WeightedGraph graph) {
		verticesToNeighbors.clear();
		if (graph.getEdges().size() < 1) {
			return;
		}
		for (final Edge edge : graph.getEdges()) {
			addNeighbors(edge);
		}
	}

	public boolean areAdjacent(Vertex vertex1, Vertex vertex2) {
		return getNeighbors(vertex1).contains(vertex2);
	}

	public int getDegree(Vertex vertex) {
		return getNeighbors(vertex).size();
	}

}
