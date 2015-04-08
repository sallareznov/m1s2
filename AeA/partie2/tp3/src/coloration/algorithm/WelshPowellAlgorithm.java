package coloration.algorithm;

import java.io.IOException;
import java.util.List;

import coloration.bean.Vertex;
import coloration.bean.WeightedGraph;
import coloration.export.TextualGraphTools;
import coloration.sort.GraphSorter;
import coloration.util.NeighborsManager;
import coloration.util.VertexNeighbor;

public class WelshPowellAlgorithm {

	public void colorGraph(WeightedGraph graph)
			throws CloneNotSupportedException {
		final NeighborsManager neighborsManager = new NeighborsManager();
		neighborsManager.initNeighbors(graph);
		final GraphSorter graphSorter = new GraphSorter();
		final List<Vertex> sortedVertexes = graphSorter.sort(graph,
				neighborsManager);
		int currentColor = 0;
		for (final Vertex vertex : sortedVertexes) {
			System.out.println(vertex);
			setSmallestNotUsedColor(vertex,
					neighborsManager, currentColor);
			if (vertex.getColor() > 0) {
				currentColor++;
			}
		}
	}

	public void setSmallestNotUsedColor(Vertex vertex,
			NeighborsManager neighborsManager, int currentColor) {
		int smallestColor = Integer.MAX_VALUE;
		for (final VertexNeighbor neighbor : neighborsManager
				.getNeighbors(vertex)) {
			System.out.println(neighbor.getNeighbor() + " __ " + neighbor.getNeighbor().getColor());
			final int color = neighbor.getNeighbor().getColor();
			if (color >= 0)
				smallestColor = Math.min(smallestColor, color);
		}
		if (smallestColor > 0) {
			vertex.setColor(0);
		}
		else {
			vertex.setColor(currentColor + 1);
		}
		System.out.println("color = " + vertex.getColor());
	}

	public static void main(String[] args) throws IOException,
			CloneNotSupportedException {
		final WelshPowellAlgorithm algo = new WelshPowellAlgorithm();
		final TextualGraphTools graphTools = new TextualGraphTools();
		final WeightedGraph graph = graphTools.getGraphFromFile("ex2.grp");
		algo.colorGraph(graph);
		for (final Vertex vertex : graph.getVertexes()) {
			System.out.println(vertex + " -> " + vertex.getColor());
		}
	}

}
