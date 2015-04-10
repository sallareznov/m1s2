package coloration.color;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import coloration.bean.Vertex;
import coloration.bean.WeightedGraph;

public class ColoursHolder {
	
	private Map<Vertex, Integer> verticesToColours;
	
	public ColoursHolder() {
		verticesToColours = new HashMap<Vertex, Integer>();
	}
	
	public Map<Vertex, Integer> getVerticesToColours() {
		return verticesToColours;
	}
	
	public void fadeGraph(WeightedGraph graph) {
		verticesToColours.clear();
		for (final Vertex vertex : graph.getVertices()) {
			verticesToColours.put(vertex, -1);
		}
	}
	
	public void colorVertex(Vertex vertex, Integer color) {
		verticesToColours.put(vertex, color);
	}
	
	public boolean isColored(Vertex vertex) {
		return getColour(vertex) != -1;
	}
	
	public int getColour(Vertex vertex) {
		return verticesToColours.get(vertex);
	}
	
	public boolean containsNotColoredVertices(WeightedGraph graph) {
		for (final Vertex vertex : graph.getVertices()) {
			if (!isColored(vertex))
				return true;
		}
		return false;
	}
	
	public void print() {
		for (final Entry<Vertex, Integer>entry : verticesToColours.entrySet())  {
			System.out.println(entry.getKey() + " -> " + entry.getValue());
		}
	}

}
