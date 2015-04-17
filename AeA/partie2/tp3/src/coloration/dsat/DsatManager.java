package coloration.dsat;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import coloration.bean.Vertex;
import coloration.bean.WeightedGraph;
import coloration.color.ColoursHolder;
import coloration.neighbor.NeighborsManager;

public class DsatManager {
	
	private Map<Vertex, Integer> dsatPerVertex;
	private Map<Vertex, Set<Integer>> colorsInNeighborhood;
	
	public DsatManager() {
		dsatPerVertex = new HashMap<Vertex, Integer>();
		colorsInNeighborhood = new HashMap<Vertex, Set<Integer>>();
	}
	
	public void resetGraph(WeightedGraph graph) {
		dsatPerVertex.clear();
		colorsInNeighborhood.clear();
		for (Vertex vertex : graph.getVertices()) {
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
		final Integer vertexColour = coloursHolder.getColour(vertex);
		for (final Vertex neighbor : neighborsManager.getNeighbors(vertex)) {
			if (!coloursHolder.isColored(neighbor) && !colorsInNeighborhood.containsKey(vertexColour))
				incrDsat(neighbor);
		}
	}

}
