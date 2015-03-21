package mst;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class WeightedGraph implements Cloneable {

	private Set<Vertex> vertexes;
	private List<Ridge> ridges;

	public WeightedGraph(Set<Vertex> vertexes, List<Ridge> ridges) {
		this.vertexes = vertexes;
		this.ridges = ridges;
	}

	public WeightedGraph() {
		this.vertexes = new HashSet<Vertex>();
		this.ridges = new LinkedList<Ridge>();
	}

	public int getSize() {
		return vertexes.size();
	}
	
	public int getTotalWeight() {
		int totalWeight = 0;
		for (Ridge ridge : ridges) {
			totalWeight += ridge.getWeight();
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

	public List<Ridge> getRidges() {
		return ridges;
	}
	
	public Ridge getRidge(Vertex vertex1, Vertex vertex2) {
		for (final Ridge ridge : ridges) {
			if ((ridge.getVertex1().equals(vertex1) && ridge.getVertex2().equals(vertex2)) || (ridge.getVertex1().equals(vertex2) && ridge.getVertex2().equals(vertex1)))
				return ridge;
		}
		return null;
	}

	public void addRidge(Ridge ridge) {
		ridges.add(ridge);
		addVertex(ridge.getVertex1());
		addVertex(ridge.getVertex2());
	}
	
	public boolean containsRidge(Ridge ridge) {
		return ridges.contains(ridge);
	}

	public void addVertex(Vertex vertex) {
		vertexes.add(vertex);
	}

	public void addRidge(Vertex vertex1, Vertex vertex2, int valeur) {
		ridges.add(new Ridge(vertex1, vertex2, valeur));
		addVertex(vertex1);
		addVertex(vertex2);
	}

	public void removeRidge(Ridge ridge) {
		ridges.remove(ridge);
	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		for (Ridge ridge : ridges) {
			builder.append(ridge.getVertex1() + " --- " + ridge.getVertex2()
					+ " --- " + ridge.getWeight() + "\n");
		}
		return builder.toString();
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		final WeightedGraph clone = new WeightedGraph();
		for (Vertex vertex : vertexes) {
			clone.addVertex(vertex);
		}
		for (Ridge ridge : ridges) {
			clone.addRidge(ridge);
		}
		return clone;
	}

}
