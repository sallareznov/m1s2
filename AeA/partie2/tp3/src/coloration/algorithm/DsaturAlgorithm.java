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
import coloration.sort.VerticesComparator;

public class DsaturAlgorithm extends AbstractGreedyAlgorithm {

	@Override
	public void colourVertices(WeightedGraph graph,
			NeighborsManager neighborsManager, ColoursHolder coloursHolder)
			throws CloneNotSupportedException {
		final GraphSorter graphSorter = new GraphSorter();
		final List<Vertex> sortedVertices = graphSorter.sort(graph,
				neighborsManager);
		final Vertex firstVertex = sortedVertices.get(0);
		final DsatManager dsatManager = new DsatManager();
		dsatManager.resetGraph(graph);
		coloursHolder.colorVertex(firstVertex, 0);
		dsatManager.updateDsat(firstVertex, neighborsManager, coloursHolder);
		incrementNbColoursUsed();
		while (coloursHolder.containsNotColoredVertices(graph)) {
			final Vertex chosenVertex = getVertexWithBiggestDsat(
					sortedVertices, neighborsManager, coloursHolder,
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

	public Vertex getVertexWithBiggestDsat(List<Vertex> sortedVertices,
			NeighborsManager neighborsManager, ColoursHolder coloursHolder,
			DsatManager dsatManager) {
		final Queue<Vertex> possibleVertices = new PriorityQueue<Vertex>(
				sortedVertices.size(), new VerticesComparator(neighborsManager));
		int dsatMax = 1;
		Vertex chosenVertex = null;
		Vertex lastMax = null;
		for (final Vertex vertex : sortedVertices) {
			final int dsatVertex = dsatManager.getDsat(vertex);
			if (!coloursHolder.isColored(vertex)) {
				if (dsatVertex >= dsatMax) {
					if (dsatVertex == dsatMax) {
						possibleVertices.add(vertex);
						if (lastMax != null)
							possibleVertices.add(lastMax);
					}
					chosenVertex = vertex;
					dsatMax = dsatVertex;
					lastMax = vertex;
				}
			}
		}
		if (possibleVertices.size() > 1) {
			chosenVertex = possibleVertices.remove();
		}
		return chosenVertex;
	}

	@Override
	public String toString() {
		return "Dsatur algorithm";
	}

}
