package coloration.util;

import java.util.HashMap;
import java.util.Map;

import coloration.bean.Vertex;
import coloration.bean.WeightedGraph;

public class ColoursHolder {
	
	private Map<Vertex, Integer> vertexesToColours;
	
	public ColoursHolder() {
		vertexesToColours = new HashMap<Vertex, Integer>();
	}
	
	public Map<Vertex, Integer> getVertexesToColours() {
		return vertexesToColours;
	}
	
	public void fadeGraph(WeightedGraph graph) {
		vertexesToColours.clear();
		for (final Vertex vertex : graph.getVertexes()) {
			vertexesToColours.put(vertex, -1);
		}
	}
	
	public void colorVertex(Vertex vertex, Integer color) {
		vertexesToColours.put(vertex, color);
	}
	
	public boolean isColored(Vertex vertex) {
		return vertexesToColours.containsKey(vertex);
	}
	
	public int getColour(Vertex vertex) {
		return vertexesToColours.get(vertex);
	}

}
