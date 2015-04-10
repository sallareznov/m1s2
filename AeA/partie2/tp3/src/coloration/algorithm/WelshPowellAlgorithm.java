package coloration.algorithm;

import java.util.List;

import coloration.bean.Vertex;
import coloration.bean.WeightedGraph;
import coloration.color.ColoursHolder;
import coloration.neighbor.NeighborsManager;
import coloration.sort.GraphSorter;

public class WelshPowellAlgorithm extends AbstractGreedyAlgorithm {
	
	@Override
	public void colourVertices(WeightedGraph graph, NeighborsManager neighborsManager,
			ColoursHolder coloursHolder)
			throws CloneNotSupportedException {
		final GraphSorter graphSorter = new GraphSorter();
		final List<Vertex> sortedVertices = graphSorter.sort(graph,
				neighborsManager);
		for (final Vertex vertex : sortedVertices) {
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
		return "Welsh-Powell's algorithm";
	}
	
}
