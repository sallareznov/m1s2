package coloration.dsat;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import coloration.bean.Vertex;
import coloration.bean.WeightedGraph;
import coloration.color.ColoursHolder;
import coloration.neighbor.NeighborsManager;

public class DsatManager {
	
	private Map<Vertex, Integer> dsatPerVertex;
	
	public DsatManager() {
		dsatPerVertex = new HashMap<Vertex, Integer>();
	}
	
	public void resetGraph(WeightedGraph graph) {
		dsatPerVertex.clear();
		for (Vertex vertex : graph.getVertexes()) {
			dsatPerVertex.put(vertex, 0);
		}
	}
	
	public int getDsat(Vertex vertex) {
		return dsatPerVertex.get(vertex);
	}
	
	public void incrDsat(Vertex vertex) {
		final int previousDsat = getDsat(vertex);
		dsatPerVertex.put(vertex, previousDsat + 1);
	}
	
	public void updateDsat(Vertex vertex, NeighborsManager neighborsManager,
			ColoursHolder coloursHolder) {
		for (Vertex neighbor : neighborsManager.getNeighbors(vertex)) {
			if (!coloursHolder.isColored(neighbor))
				incrDsat(neighbor);
		}
	}
	
	public void print() {
		for (final Entry<Vertex, Integer>entry : dsatPerVertex.entrySet())  {
			System.out.println(entry.getKey() + " -> " + entry.getValue());
		}
	}

}
