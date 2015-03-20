package mst;

import java.util.LinkedList;
import java.util.List;

public class WeightedGraph implements Cloneable {

	private Vertex[] vertexes;
	private List<Ridge> ridges;

	public WeightedGraph(Vertex[] sommets) {
		this.vertexes = sommets;
		this.ridges = new LinkedList<Ridge>();
	}

	public WeightedGraph() {
		this.vertexes = getVertexes();
		this.ridges = new LinkedList<Ridge>();
	}

	public int getSize() {
		return vertexes.length;
	}

	public Vertex[] getVertexes() {
		if (vertexes != null) {
			return vertexes;
		}
		final List<Vertex> setVertexes = new LinkedList<Vertex>();
		for (Ridge ridge : ridges) {
			final Vertex vertex1 = ridge.getVertex1();
			final Vertex vertex2 = ridge.getVertex2();
			if (setVertexes.contains(vertex1))
				setVertexes.add(vertex1);
			if (setVertexes.contains(vertex2))
				setVertexes.add(vertex2);
		}
		return setVertexes.toArray(new Vertex[setVertexes.size()]);
	}

	public List<Ridge> getRidges() {
		return ridges;
	}

	public Vertex getVertex(int i) {
		return vertexes[i];
	}

	public void addRidge(Vertex sommet1, Vertex sommet2, int valeur) {
		ridges.add(new Ridge(sommet1, sommet2, valeur));
	}
	
	public void removeRidge(Ridge ridge) {
		ridges.remove(ridge);
		vertexes = getVertexes();
	}

	public void addRidge(Ridge ridge) {
		ridges.add(ridge);
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		final WeightedGraph clone = new WeightedGraph(vertexes);
		for (Ridge ridge : ridges) {
			clone.addRidge(ridge);
		}
		return clone;
	}

}
