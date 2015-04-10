package coloration.color;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

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
		return getColour(vertex) != -1;
	}
	
	public int getColour(Vertex vertex) {
		return vertexesToColours.get(vertex);
	}
	
	public boolean containsNotColoredVertexes(WeightedGraph graph) {
		for (final Vertex vertex : graph.getVertexes()) {
			if (!isColored(vertex))
				return true;
		}
		return false;
	}
	
	public void print() {
		for (final Entry<Vertex, Integer>entry : vertexesToColours.entrySet())  {
			System.out.println(entry.getKey() + " -> " + entry.getValue());
		}
	}

}
