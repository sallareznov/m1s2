package mst.util;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import mst.bean.Vertex;
import mst.bean.WeightedGraph;

public class CycleDetector {

	private Map<Vertex, Integer> levels;

	public CycleDetector() {
		levels = new HashMap<Vertex, Integer>();
	}

	public void initLevels(WeightedGraph graph) {
		for (Vertex vertex : graph.getVertices()) {
			levels.put(vertex, -1);
		}
	}

	public int getLevel(Vertex vertex) {
		return levels.get(vertex);
	}

	public boolean hasACycle(WeightedGraph graph, Vertex vertex) {
		levels.clear();
		boolean cyclic = false;
		final NeighborsManager neighborsManager = new NeighborsManager();
		neighborsManager.initNeighbors(graph);
		initLevels(graph);
		final Queue<Vertex> queueVertices = new LinkedList<Vertex>();
		levels.put(vertex, 0);
		queueVertices.add(vertex);
		while (!queueVertices.isEmpty() && !cyclic) {
			final Vertex topVertex = queueVertices.remove();
			for (VertexNeighbor neighbor : neighborsManager.getNeighbors(topVertex)) {
				if (getLevel(neighbor.getNeighbor()) == -1) {
					levels.put(neighbor.getNeighbor(), getLevel(topVertex) + 1);
					queueVertices.add(neighbor.getNeighbor());
				} else if (getLevel(topVertex) <= getLevel(neighbor.getNeighbor())) {
						cyclic = true;
				}
			}
		}
		return cyclic;
	}

}
