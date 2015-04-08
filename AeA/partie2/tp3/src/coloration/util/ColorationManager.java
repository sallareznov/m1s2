package coloration.util;

import java.util.HashMap;
import java.util.Map;

import coloration.bean.Vertex;
import coloration.bean.WeightedGraph;

public class ColorationManager {
	
	private Map<Vertex, Integer> vertexesToColors;
	
	public ColorationManager() {
		vertexesToColors = new HashMap<Vertex, Integer>();
	}
	
	public void initColors(WeightedGraph graph) {
		for (final Vertex vertex : graph.getVertexes()) {
			vertexesToColors.put(vertex, -1);
		}
	}
	
	public void colourVertex(Vertex vertex, Integer color) {
		vertexesToColors.put(vertex, color);
	}
	
	public boolean isColored(Vertex vertex) {
		return vertexesToColors.containsKey(vertex);
	}
	
	public int getColor(Vertex vertex) {
		return vertexesToColors.get(vertex);
	}

}
