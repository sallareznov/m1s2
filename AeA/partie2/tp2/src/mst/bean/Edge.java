package mst.bean;

public class Edge {

	private Vertex vertex1;
	private Vertex vertex2;
	private int weight;

	public Edge(Vertex vertex1, Vertex vertex2, int weight) {
		this.vertex1 = vertex1;
		this.vertex2 = vertex2;
		this.weight = weight;
	}

	public Vertex getVertex1() {
		return vertex1;
	}

	public Vertex getVertex2() {
		return vertex2;
	}

	public int getWeight() {
		return weight;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((vertex1 == null) ? 0 : vertex1.hashCode());
		result = prime * result + ((vertex2 == null) ? 0 : vertex2.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!getClass().equals(obj.getClass())) {
			return false;
		}
		final Edge edge = (Edge) obj;
		final boolean condition1 = vertex1.equals(edge.getVertex1()) && vertex2.equals(edge.getVertex2());
		final boolean condition2 = vertex1.equals(edge.getVertex2()) && vertex2.equals(edge.getVertex1());
		return condition1 || condition2;
	}
	
	@Override
	public String toString() {
		return vertex1 + " -- " + vertex2 + " : " + weight;
	}

}
