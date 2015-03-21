package mst;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Queue;

public class NeighborsManager {

	private Map<Vertex, List<Vertex>> vertexesToNeighbors;

	public NeighborsManager() {
		vertexesToNeighbors = new HashMap<Vertex, List<Vertex>>();
	}

	public void addNeighbors(Vertex vertex1, Vertex vertex2) {
		List<Vertex> vertex1Neighbors = vertexesToNeighbors.get(vertex1);
		if (vertex1Neighbors == null) {
			vertex1Neighbors = new LinkedList<Vertex>();
		}
		vertex1Neighbors.add(vertex2);
		vertexesToNeighbors.put(vertex1, vertex1Neighbors);
	}

	public List<Vertex> getNeighbors(Vertex vertex) {
		final List<Vertex> neighbors = vertexesToNeighbors.get(vertex);
		if (neighbors == null) {
			return new LinkedList<Vertex>();
		}
		return neighbors;
	}

	public Queue<Vertex> getNeighbors(WeightedGraph graph) {
		return null;
	}

	public void addNeighbors(Ridge ridge) {
		addNeighbors(ridge.getVertex1(), ridge.getVertex2());
		addNeighbors(ridge.getVertex2(), ridge.getVertex1());
	}

	public void initNeighbors(WeightedGraph graph) {
		vertexesToNeighbors.clear();
		final Queue<Ridge> graphRidges = new PriorityQueue<Ridge>(
				graph.getSize(), new RidgeComparator());
		graphRidges.addAll(graph.getRidges());
		while (!graphRidges.isEmpty()) {
			final Ridge poll = graphRidges.poll();
			addNeighbors(poll);
		}
	}

	public Ridge getWeakerOutgoingRidge(WeightedGraph subGraph, WeightedGraph graph) {
		final Queue<Ridge> graphRidges = new PriorityQueue<Ridge>(
				subGraph.getSize(), new RidgeComparator());
		for (Vertex vertex : subGraph.getVertexes()) {
			for (Vertex neighbor : getNeighbors(vertex)) {
				final Ridge ridge = graph.getRidge(vertex, neighbor);
				graphRidges.add(ridge);
			}
		}
		return graphRidges.peek();
	}

	public void removeNeighbor(Vertex vertex) {
		for (final Entry<Vertex, List<Vertex>> entry : vertexesToNeighbors
				.entrySet()) {
			final List<Vertex> currentNeighbors = entry.getValue();
			currentNeighbors.remove(vertex);
		}
	}

	public boolean isANeighbor(Vertex vertex1, Vertex vertex2) {
		final List<Vertex> vertex2Neighbors = vertexesToNeighbors.get(vertex2);
		return vertex2Neighbors.contains(vertex1);
	}

}