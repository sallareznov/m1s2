package mst;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class NeighborsManager {

	private Map<Vertex, Set<Vertex>> vertexesToNeighbors;

	public NeighborsManager() {
		vertexesToNeighbors = new HashMap<Vertex, Set<Vertex>>();
	}

	public void addNeighbors(Vertex vertex1, Vertex vertex2) {
		Set<Vertex> vertex1Neighbors = vertexesToNeighbors.get(vertex1);
		if (vertex1Neighbors == null) {
			vertex1Neighbors = new HashSet<Vertex>();
		}
		vertex1Neighbors.add(vertex2);
		vertexesToNeighbors.put(vertex1, vertex1Neighbors);
	}
	
	public Set<Vertex> getNeighbors(Vertex vertex) {
		final Set<Vertex> neighbors = vertexesToNeighbors.get(vertex); 
		if (neighbors == null) {
			return new HashSet<Vertex>();
		}
		return neighbors;
	}
	
	public void addNeighbors(Ridge ridge) {
		addNeighbors(ridge.getVertex1(), ridge.getVertex2());
		addNeighbors(ridge.getVertex2(), ridge.getVertex1());
	}

	public void initNeighbors(WeightedGraph graph) {
		vertexesToNeighbors.clear();
		final List<Ridge> graphRidges = graph.getRidges();
		for (final Ridge ridge : graphRidges) {
			addNeighbors(ridge);
		}
	}
	
	public boolean isANeighbor(Vertex vertex1, Vertex vertex2) {
		final Set<Vertex> vertex2Neighbors = vertexesToNeighbors.get(vertex2);
		return vertex2Neighbors.contains(vertex1);
	}
	
}