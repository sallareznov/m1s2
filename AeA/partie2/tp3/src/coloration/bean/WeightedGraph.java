package coloration.bean;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import coloration.color.ColoursHolder;

public class WeightedGraph implements Cloneable {

	private Set<Vertex> vertexes;
	private List<Edge> edges;
	private Map<Edge, Edge> edgesToEdges;

	public WeightedGraph(Set<Vertex> vertexes, List<Edge> edges) {
		this.vertexes = vertexes;
		this.edges = edges;
		edgesToEdges = new HashMap<Edge, Edge>();
		for (final Edge edge : edges) {
			edgesToEdges.put(edge, edge);
		}
	}

	public WeightedGraph() {
		vertexes = new HashSet<Vertex>();
		edges = new LinkedList<Edge>();
		edgesToEdges = new HashMap<Edge, Edge>();
	}

	public int getSize() {
		return vertexes.size();
	}
	
	public boolean containsNotColoredVertexes(ColoursHolder colorationManager) {
		for (final Vertex vertex : vertexes) {
			if (!colorationManager.isColored(vertex))
				return true;
		}
		return false;
	}

	public Set<Vertex> getVertexes() {
		return vertexes;
	}

	public Vertex getVertex(int i) {
		if (i >= getSize()) {
			throw new IllegalArgumentException();
		}
		int currentIndex = 0;
		final Iterator<Vertex> iterVertexes = vertexes.iterator();
		while (iterVertexes.hasNext() && currentIndex < i) {
			iterVertexes.next();
			currentIndex++;
		}
		return iterVertexes.next();
	}

	public List<Edge> getEdges() {
		return edges;
	}

	public Edge getEdge(Vertex vertex1, Vertex vertex2) {
		Edge edge = edgesToEdges.get(new Edge(vertex1, vertex2));
		if (edge == null) {
			edge = edgesToEdges.get(new Edge(vertex2, vertex1));
		}
		return edge;
	}

	public void addEdge(Edge edge) {
		if (edges.contains(edge)) {
			return;
		}
		edges.add(edge);
		edgesToEdges.put(edge, edge);
		addVertex(edge.getVertex1());
		addVertex(edge.getVertex2());
	}

	public void addVertex(Vertex vertex) {
		vertexes.add(vertex);
	}
	
	public void removeVertexes() {
		vertexes.clear();
	}

	public void addEdge(Vertex vertex1, Vertex vertex2) {
		final Edge edgeToAdd = new Edge(vertex1, vertex2);
		edges.add(edgeToAdd);
		edgesToEdges.put(edgeToAdd, edgeToAdd);
		addVertex(vertex1);
		addVertex(vertex2);
	}

	public void removeEdge(Edge edge) {
		edges.remove(edge);
		edgesToEdges.remove(edge);
	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		for (Edge edge : edges) {
			builder.append(edge.getVertex1() + " --- " + edge.getVertex2() + "\n");
		}
		return builder.toString();
	}

	public boolean containsEdge(Edge edge) {
		return edgesToEdges.containsKey(edge);
	}

	public boolean containsVertexes(Edge edge) {
		boolean condition1 = false;
		boolean condition2 = false;
		final Iterator<Vertex> iter = vertexes.iterator();
		while (iter.hasNext() && !(condition1 && condition2)) {
			final Vertex vertex = iter.next();
			if (vertex.equals(edge.getVertex1()))
				condition1 = true;
			if (vertex.equals(edge.getVertex2()))
				condition2 = true;
		}
		return condition1 && condition2;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		super.clone();
		final WeightedGraph clone = new WeightedGraph();
		for (Vertex vertex : vertexes) {
			clone.addVertex(vertex);
		}
		for (Edge edge : edges) {
			clone.addEdge(edge);
		}
		return clone;
	}

}
