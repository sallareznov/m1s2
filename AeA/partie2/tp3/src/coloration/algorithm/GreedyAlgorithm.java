package coloration.algorithm;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import coloration.bean.Vertex;
import coloration.bean.WeightedGraph;
import coloration.util.ColoursHolder;
import coloration.util.NeighborsManager;

public class GreedyAlgorithm extends AbstractGreedyAlgorithm {

	@Override
	public void colourVertexes(WeightedGraph graph,
			NeighborsManager neighborsManager, ColoursHolder coloursHolder)
			throws CloneNotSupportedException {
		final List<Vertex> vertexes = new LinkedList<Vertex>(graph.getVertexes()); 
		final List<Vertex> sortedVertexes = new LinkedList<Vertex>();
		final Random random = new Random();
		while (!vertexes.isEmpty()) {
			final int randomIndex = random.nextInt(vertexes.size());
			final Vertex randomVertex = vertexes.get(randomIndex);
			sortedVertexes.add(randomVertex);
			vertexes.remove(randomIndex);
		}
		resetNbUsedColours();
		for (final Vertex vertex : sortedVertexes) {
			setSmallestNotUsedColour(vertex, neighborsManager,
					coloursHolder);
			if (coloursHolder.getColour(vertex) == getNbUsedColours()) {
				incrementNbUsedColours();
			}
		}
		updateExecutionTime(System.currentTimeMillis());
	}

}
