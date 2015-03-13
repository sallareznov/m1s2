package mst;


public class Ridge {
	
	private Vertex vertex1;
	private Vertex vertex2;
	private int valeur;
	
	public Ridge(Vertex vertex1, Vertex vertex2, int weight) {
		this.vertex1 = vertex1;
		this.vertex2 = vertex2;
		this.valeur = weight;
	}
	
	public Vertex getVertex1() {
		return vertex1;
	}
	
	public Vertex getVertex2() {
		return vertex2;
	}
	
	public int getWeight() {
		return valeur;
	}

}
