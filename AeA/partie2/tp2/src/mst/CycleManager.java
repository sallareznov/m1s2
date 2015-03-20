package mst;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class CycleManager {

	private Map<Vertex, Integer> levels;

	public CycleManager() {
		levels = new HashMap<Vertex, Integer>();
	}

	public void initLevels(WeightedGraph graph) {
		for (Vertex vertex : graph.getVertexes()) {
			levels.put(vertex, -1);
		}
	}

	public int getLevel(Vertex vertex) {
		return levels.get(vertex);
	}

	public boolean isAcyclic(WeightedGraph graph, Vertex vertex) {
		levels.clear();
		boolean acyclic = true;
		final NeighborsManager neighborsManager = new NeighborsManager();
		neighborsManager.initNeighbors(graph);
		initLevels(graph);
		final Queue<Vertex> queueVertexes = new LinkedList<Vertex>();
		levels.put(vertex, 0);
		queueVertexes.add(vertex);
		while (!queueVertexes.isEmpty() && acyclic) {
			final Vertex topVertex = queueVertexes.remove();
			for (Vertex neighbor : neighborsManager.getNeighbors(topVertex)) {
				if (getLevel(neighbor) == -1) {
					levels.put(neighbor, getLevel(topVertex) + 1);
					queueVertexes.add(neighbor);
				} else {
					if (getLevel(topVertex) <= getLevel(neighbor))
						acyclic = false;
				}
			}
			System.out.println(topVertex + " ");
		}
		return acyclic;
	}

}
