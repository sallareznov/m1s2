package coloration.algorithm;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import coloration.bean.Vertex;
import coloration.bean.WeightedGraph;
import coloration.export.TextualGraphTools;
import coloration.sort.GraphSorter;
import coloration.util.NeighborsManager;

public class WelshPowellAlgorithm {

	public void colorGraph(WeightedGraph graph)
			throws CloneNotSupportedException {
		final NeighborsManager neighborsManager = new NeighborsManager();
		neighborsManager.initNeighbors(graph);
		final GraphSorter graphSorter = new GraphSorter();
		final List<Vertex> sortedVertexes = graphSorter.sort(graph,
				neighborsManager);
		final List<Vertex> copy = new LinkedList<Vertex>();
		copy.addAll(sortedVertexes);
		int currentColor = 0;
		while (!copy.isEmpty()) {
			final Vertex currentVertex = copy.remove(0);
			currentVertex.setColor(currentColor);
			for (final Vertex vertex1 : sortedVertexes) {
				System.out.println("--- " + vertex1);
				if (!vertex1.isColored()) {
					for (Vertex vertex2 : sortedVertexes) {
						if (!neighborsManager.areAdjacent(vertex1, vertex2)
								&& vertex2.getColor() == currentColor) {
							vertex1.setColor(currentColor);
							System.out.println("vertex1 = " + vertex1);
							System.out.println("vertex2 = " + vertex2);
							System.out.println("-> " + vertex1);
							copy.remove(vertex1);
							break;
						}
					}
				}
			}
			for (Vertex vertex : graph.getVertexes()) {
				System.out.println(vertex + " : " + vertex.getColor());
			}
			currentColor++;
			System.out.println("..");
		}
		for (Vertex vertex : graph.getVertexes()) {
			System.out.println(vertex + " : " + vertex.getColor());
		}
	}

	public static void main(String[] args) throws IOException,
			CloneNotSupportedException {
		final WelshPowellAlgorithm algo = new WelshPowellAlgorithm();
		final TextualGraphTools graphTools = new TextualGraphTools();
		final WeightedGraph graph = graphTools.getGraphFromFile("ex2.grp");
		algo.colorGraph(graph);
	}

}
