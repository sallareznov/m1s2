package coloration.algorithm;

import java.io.IOException;
import java.util.List;

import coloration.bean.Vertex;
import coloration.bean.WeightedGraph;
import coloration.export.TextualGraphTools;
import coloration.sort.GraphSorter;
import coloration.util.ColorationManager;
import coloration.util.NeighborsManager;

public class WelshPowellAlgorithm implements VertexesColorationAlgorithm {

	public void colorGraph(WeightedGraph graph)
			throws CloneNotSupportedException {
		final NeighborsManager neighborsManager = new NeighborsManager();
		neighborsManager.initNeighbors(graph);
		final GraphSorter graphSorter = new GraphSorter();
		final List<Vertex> sortedVertexes = graphSorter.sort(graph,
				neighborsManager);
		final ColorationManager colorationManager = new ColorationManager();
		colorationManager.initColors(graph);
		int currentColor = 0;
		for (final Vertex vertex : sortedVertexes) {
			setSmallestNotUsedColor(vertex, neighborsManager,
					colorationManager, currentColor);
			if (colorationManager.getColor(vertex) > 0) {
				currentColor++;
			}
		}
	}

	public void setSmallestNotUsedColor(Vertex vertex,
			NeighborsManager neighborsManager,
			ColorationManager colorationManager, int currentColor) {
		int smallestColor = Integer.MAX_VALUE;
		for (final Vertex neighbor : neighborsManager.getNeighbors(vertex)) {
			final Integer color = colorationManager.getColor(neighbor);
			if (color != -1)
				smallestColor = Math.min(smallestColor, color);
		}
		if (smallestColor > 0) {
			colorationManager.colourVertex(vertex, 0);
		} else {
			for (int i = 0 ; i <= currentColor + 1 ; i++) {
				boolean usedColor = false;
				for (final Vertex neighbor : neighborsManager.getNeighbors(vertex)) {
					if (colorationManager.getColor(neighbor) == i) {
						usedColor = true;
						break;
					}
				}
				if (!usedColor) {
					colorationManager.colourVertex(vertex, i);
					break;
				}
			}
		}
	}

	public static void main(String[] args) throws IOException,
			CloneNotSupportedException {
		final WelshPowellAlgorithm algo = new WelshPowellAlgorithm();
		final TextualGraphTools graphTools = new TextualGraphTools();
		final WeightedGraph graph = graphTools.getGraphFromFile("petersen.grp");
		algo.colorGraph(graph);
	}

}
