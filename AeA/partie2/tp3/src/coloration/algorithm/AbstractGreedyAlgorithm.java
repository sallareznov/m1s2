package coloration.algorithm;

import coloration.bean.Vertex;
import coloration.bean.WeightedGraph;
import coloration.util.ColoursHolder;
import coloration.util.NeighborsManager;

public abstract class AbstractGreedyAlgorithm implements VertexesColorationAlgorithm {
	
	private int nbUsedColours;
	private long executionTime;

	public void setSmallestNotUsedColour(Vertex vertex,
			NeighborsManager neighborsManager,
			ColoursHolder coloursHolder) {
		int smallestColour = Integer.MAX_VALUE;
		for (final Vertex neighbor : neighborsManager.getNeighbors(vertex)) {
			final Integer color = coloursHolder.getColour(neighbor);
			if (color != -1)
				smallestColour = Math.min(smallestColour, color);
		}
		if (smallestColour > 0) {
			coloursHolder.colorVertex(vertex, 0);
		} else {
			for (int i = 0; i <= nbUsedColours + 1; i++) {
				boolean usedColour = false;
				for (final Vertex neighbor : neighborsManager
						.getNeighbors(vertex)) {
					if (coloursHolder.getColour(neighbor) == i) {
						usedColour = true;
						break;
					}
				}
				if (!usedColour) {
					coloursHolder.colorVertex(vertex, i);
					break;
				}
			}
		}
	}
	
	@Override
	public int getNbUsedColours() {
		return nbUsedColours;
	}
	
	@Override
	public void incrementNbUsedColours() {
		nbUsedColours++;
	}
	
	@Override
	public void resetNbUsedColours() {
		nbUsedColours = 0;
	}
	
	@Override
	public long getExecutionTime() {
		return executionTime;
	}
	
	@Override
	public void updateExecutionTime(long endTime) {
		executionTime = endTime - executionTime;
	}

	@Override
	public void colourGraph(WeightedGraph graph,
			ColoursHolder coloursHolder)
			throws CloneNotSupportedException {
		final NeighborsManager neighborsManager = new NeighborsManager();
		neighborsManager.initNeighbors(graph);
		coloursHolder.fadeGraph(graph);
		executionTime = System.currentTimeMillis();
		colourVertexes(graph, neighborsManager, coloursHolder);
	}

	public abstract void colourVertexes(WeightedGraph graph,
			NeighborsManager neighborsManager,
			ColoursHolder coloursHolder)
			throws CloneNotSupportedException;

}
