package coloration.algorithm;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import coloration.bean.Vertex;
import coloration.bean.WeightedGraph;
import coloration.color.ColoursHolder;
import coloration.neighbor.NeighborsManager;

public class GreedyAlgorithm extends AbstractGreedyAlgorithm {

	@Override
	public void colourVertices(WeightedGraph graph,
			NeighborsManager neighborsManager, ColoursHolder coloursHolder)
			throws CloneNotSupportedException {
		final List<Vertex> vertices = new LinkedList<Vertex>(graph.getVertices());
		Collections.shuffle(vertices);
		for (final Vertex vertex : vertices) {
			setSmallestNotUsedColour(vertex, neighborsManager,
					coloursHolder);
			if (coloursHolder.getColour(vertex) == getNbColoursUsed()) {
				incrementNbColoursUsed();
			}
		}
		updateExecutionTime(System.currentTimeMillis());
	}
	
	@Override
	public String toString() {
		return "Greedy algorithm";
	}

}
