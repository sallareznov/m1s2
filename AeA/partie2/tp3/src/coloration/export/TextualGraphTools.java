package coloration.export;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import coloration.bean.Edge;
import coloration.bean.Vertex;
import coloration.bean.WeightedGraph;
import coloration.neighbor.NeighborsManager;

public class TextualGraphTools {

	private static final String TOKEN_SEPARATOR = " ";

	public WeightedGraph getGraphFromFile(String filename) throws IOException {
		final BufferedReader reader = new BufferedReader(new FileReader(
				filename));
		final WeightedGraph graph = new WeightedGraph();
		String line = null;
		while ((line = reader.readLine()) != null) {
			treatLine(graph, line);
		}
		reader.close();
		return graph;
	}

	private void treatLine(WeightedGraph graph, String line) {
		final StringTokenizer tokenizer = new StringTokenizer(line,
				TOKEN_SEPARATOR);
		final Vertex vertex1 = new Vertex(tokenizer.nextToken());
		while (tokenizer.hasMoreTokens()) {
			final Vertex vertex2 = new Vertex(tokenizer.nextToken());
			graph.addEdge(new Edge(vertex1, vertex2));
		}
	}

	public void exportGraphToFile(WeightedGraph graph, String filename)
			throws IOException {
		final BufferedWriter writer = new BufferedWriter(new FileWriter(
				filename));
		final NeighborsManager neighborsManager = new NeighborsManager();
		final List<Edge> printedEdges = new LinkedList<Edge>();
		neighborsManager.initNeighbors(graph);
		for (final Vertex vertex : graph.getVertexes()) {
			treatVertex(vertex, writer, neighborsManager, printedEdges);
		}
		writer.close();
	}

	private void treatVertex(Vertex vertex, BufferedWriter writer,
			NeighborsManager neighborsManager, List<Edge> printedEdges)
			throws IOException {
		writer.write(vertex.toString());
		for (final Vertex neighbor : neighborsManager
				.getNeighbors(vertex)) {
			if (!printedEdges.contains(new Edge(vertex, neighbor))) {
				writer.write(TOKEN_SEPARATOR + neighbor);
				printedEdges.add(new Edge(vertex, neighbor));
			}
		}
		writer.write("\n");
	}

}
