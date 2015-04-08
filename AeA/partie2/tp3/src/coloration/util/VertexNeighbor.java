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

	@Override
	public boolean equals(Object obj) {
		if (!getClass().equals(obj.getClass())) {
			return false;
		}
		final VertexNeighbor neighbor = (VertexNeighbor) obj;
		return neighbor.getNeighbor().equals(this.neighbor);
	}

}
