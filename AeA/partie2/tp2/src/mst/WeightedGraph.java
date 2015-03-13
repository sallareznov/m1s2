package mst;

import java.util.HashSet;
import java.util.Set;

public class WeightedGraph {
	
	private Vertex[] vertexes;
	private Set<Ridge> ridges;
	
	public WeightedGraph(Vertex[] sommets) {
		this.vertexes = sommets;
		this.ridges = new HashSet<Ridge>();
	}
	
	public int getSize() {
		return vertexes.length;
	}
	
	public Vertex[] getVertexes() {
		return vertexes;
	}
	
	public Set<Ridge> getRidges() {
		return ridges;
	}
	
	public Vertex getVertex(int i) {
		return vertexes[i];
	}
	
	public void addRidge(Vertex sommet1, Vertex sommet2, int valeur) {
		ridges.add(new Ridge(sommet1, sommet2, valeur));
	}
	
}
