package coloration.util;

import coloration.bean.Vertex;

public class VertexNeighbor {
	
	private Vertex neighbor;
	private int weight;
	
	public VertexNeighbor(Vertex neighbor, int weight) {
		this.neighbor = neighbor;
		this.weight = weight;
	}
	
	public Vertex getNeighbor() {
		return neighbor;
	}
	
	public int getWeight() {
		return weight;
	}

}
