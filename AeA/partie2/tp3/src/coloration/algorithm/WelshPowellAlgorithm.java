package coloration.algorithm;

import java.util.List;

import coloration.bean.Vertex;
import coloration.bean.WeightedGraph;
import coloration.sort.GraphSorter;
import coloration.util.ColoursHolder;
import coloration.util.NeighborsManager;

public class WelshPowellAlgorithm extends GreedyAlgorithm {
	
	@Override
	public void colourVertexes(WeightedGraph graph, NeighborsManager neighborsManager,
			ColoursHolder colorsHolder)
			throws CloneNotSupportedException {
		colorsHolder.fadeGraph(graph);
		final GraphSorter graphSorter = new GraphSorter();
		final List<Vertex> sortedVertexes = graphSorter.sort(graph,
				neighborsManager);
		resetNbUsedColours();
		for (final Vertex vertex : sortedVertexes) {
			setSmallestNotUsedColour(vertex, neighborsManager,
					colorsHolder);
			if (colorsHolder.getColour(vertex) == getNbUsedColours()) {
				incrementNbUsedColours();
			}
		}
		updateExecutionTime(System.currentTimeMillis());
	}

	@Override
	public String toString() {
		return "Welsh-Powell's algorithm";
	}

}
