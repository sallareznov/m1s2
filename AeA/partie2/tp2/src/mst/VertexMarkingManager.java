package mst;

import java.util.HashMap;
import java.util.Map;

public class VertexMarkingManager {
	
	private Map<Vertex, Vertex> visitedVertexes; 
	
	public VertexMarkingManager() {
		visitedVertexes = new HashMap<Vertex, Vertex>();
	}
	
	public void mark(Vertex vertex, Vertex father) {
		visitedVertexes.put(vertex, father);
	}
	
	public boolean isMarked(Vertex vertex) {
		return visitedVertexes.containsKey(vertex);
	}
	
	public Vertex getFather(Vertex vertex) {
		return visitedVertexes.get(vertex);
	}
	
	public void reset() {
		visitedVertexes.clear();
	}

}
