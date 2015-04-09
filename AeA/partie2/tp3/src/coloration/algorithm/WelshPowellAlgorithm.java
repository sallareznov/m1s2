package coloration.algorithm;

import java.util.List;

import coloration.bean.Vertex;
import coloration.bean.WeightedGraph;
import coloration.sort.GraphSorter;
import coloration.util.ColoursHolder;
import coloration.util.NeighborsManager;

public class WelshPowellAlgorithm extends AbstractGreedyAlgorithm {
	
	@Override
	public void colourVertexes(WeightedGraph graph, NeighborsManager neighborsManager,
			ColoursHolder coloursHolder)
			throws CloneNotSupportedException {
		final GraphSorter graphSorter = new GraphSorter();
		final List<Vertex> sortedVertexes = graphSorter.sort(graph,
				neighborsManager);
		for (final Vertex vertex : sortedVertexes) {
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
		return "---> Welsh-Powell's algorithm";
	}
	
}
