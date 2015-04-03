package coloration.bean;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class WeightedGraph implements Cloneable {

	private Set<Vertex> vertexes;
	private List<Edge> edges;

	public WeightedGraph(Set<Vertex> vertexes, List<Edge> edges) {
		this.vertexes = vertexes;
		this.edges = edges;
	}

	public WeightedGraph() {
		this.vertexes = new HashSet<Vertex>();
		this.edges = new LinkedList<Edge>();
	}

	public int getSize() {
		return vertexes.size();
	}
	
	public int getTotalWeight() {
		int totalWeight = 0;
		for (Edge edge : edges) {
			totalWeight += edge.getWeight();
		}
		return totalWeight;
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
		for (final Edge edge : edges) {
			if ((edge.getVertex1().equals(vertex1) && edge.getVertex2().equals(vertex2)) || (edge.getVertex1().equals(vertex2) && edge.getVertex2().equals(vertex1)))
				return edge;
		}
		return null;
	}

	public void addEdge(Edge edge) {
		if (edges.contains(edge)) {
			return;
		}
		edges.add(edge);
		addVertex(edge.getVertex1());
		addVertex(edge.getVertex2());
	}
	
	public void addVertex(Vertex vertex) {
		vertexes.add(vertex);
	}

	public void addEdge(Vertex vertex1, Vertex vertex2, int valeur) {
		edges.add(new Edge(vertex1, vertex2, valeur));
		addVertex(vertex1);
		addVertex(vertex2);
	}

	public void removeEdge(Edge edge) {
		edges.remove(edge);
	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		for (Edge edge : edges) {
			builder.append(edge.getVertex1() + " --- " + edge.getVertex2()
					+ " --- " + edge.getWeight() + "\n");
		}
		return builder.toString();
	}
	
	public boolean containsEdge(Edge edge) {
		return edges.contains(edge);
	}
	
	public boolean containsVertexes(Edge edge) {
		return vertexes.contains(edge.getVertex1()) && vertexes.contains(edge.getVertex2()); 
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
