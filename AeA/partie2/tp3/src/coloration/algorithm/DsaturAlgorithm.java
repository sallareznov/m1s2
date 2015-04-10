package coloration.algorithm;

import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import coloration.bean.Vertex;
import coloration.bean.WeightedGraph;
import coloration.color.ColoursHolder;
import coloration.dsat.DsatManager;
import coloration.neighbor.NeighborsManager;
import coloration.sort.GraphSorter;
import coloration.sort.VertexesComparator;

public class DsaturAlgorithm extends AbstractGreedyAlgorithm {

	@Override
	public void colourVertexes(WeightedGraph graph,
			NeighborsManager neighborsManager, ColoursHolder coloursHolder)
			throws CloneNotSupportedException {
		final GraphSorter graphSorter = new GraphSorter();
		final List<Vertex> sortedVertexes = graphSorter.sort(graph,
				neighborsManager);
		final Vertex firstVertex = sortedVertexes.get(0);
		final DsatManager dsatManager = new DsatManager();
		dsatManager.resetGraph(graph);
		coloursHolder.colorVertex(firstVertex, 0);
		dsatManager.updateDsat(firstVertex, neighborsManager, coloursHolder);
		incrementNbColoursUsed();
		while (coloursHolder.containsNotColoredVertexes(graph)) {
			final Vertex chosenVertex = getVertexWithBiggestDsat(
					sortedVertexes, neighborsManager, coloursHolder,
					dsatManager);
			setSmallestNotUsedColour(chosenVertex, neighborsManager,
					coloursHolder);
			if (coloursHolder.getColour(chosenVertex) == getNbColoursUsed()) {
				incrementNbColoursUsed();
			}
			dsatManager.updateDsat(chosenVertex, neighborsManager,
					coloursHolder);
		}
		updateExecutionTime(System.currentTimeMillis());
	}

	public Vertex getVertexWithBiggestDsat(List<Vertex> sortedVertexes,
			NeighborsManager neighborsManager, ColoursHolder coloursHolder,
			DsatManager dsatManager) {
		final Queue<Vertex> possibleVertexes = new PriorityQueue<Vertex>(
				sortedVertexes.size(), new VertexesComparator(neighborsManager));
		int dsatMax = 1;
		Vertex chosenVertex = null;
		Vertex lastMax = null;
		for (final Vertex vertex : sortedVertexes) {
			final int dsatVertex = dsatManager.getDsat(vertex);
			if (!coloursHolder.isColored(vertex)) {
				if (dsatVertex >= dsatMax) {
					if (dsatVertex == dsatMax) {
						possibleVertexes.add(vertex);
						if (lastMax != null)
							possibleVertexes.add(lastMax);
					}
					chosenVertex = vertex;
					dsatMax = dsatVertex;
					lastMax = vertex;
				}
			}
		}
		if (possibleVertexes.size() > 1) {
			chosenVertex = possibleVertexes.remove();
		}
		return chosenVertex;
	}

	@Override
	public String toString() {
		return "Dsatur algorithm";
	}

}
