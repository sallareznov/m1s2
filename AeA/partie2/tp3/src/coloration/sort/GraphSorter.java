package coloration.sort;

import java.io.IOException;

import coloration.bean.Vertex;
import coloration.bean.WeightedGraph;
import coloration.exporter.GraphTools;
import coloration.util.NeighborsManager;

public class GraphSorter {

	public MinHeap<Vertex> sort(WeightedGraph graph,
			NeighborsManager neighborsManager) {
		final MinHeap<Vertex> queue = new MinHeap<Vertex>(
				graph.getSize(), new VertexesComparator(neighborsManager));
		for (final Vertex vertex : graph.getVertexes()) {
			System.out.println(vertex + " : "
					+ neighborsManager.getNeighbors(vertex).size());
			queue.add(vertex);
		}
		return queue;
	}

	public static void main(String[] args) throws IOException {
		final GraphTools graphTools = new GraphTools();
		final WeightedGraph graph = graphTools.getGraphFromFile("graph.txt");
		final GraphSorter graphSorter = new GraphSorter();
		final NeighborsManager neighborsManager = new NeighborsManager();
		neighborsManager.initNeighbors(graph);
		final MinHeap<Vertex> queue = graphSorter.sort(graph,
				neighborsManager);
		while (!queue.isEmpty()) {
			System.out.println(queue.remove());
		}
	}

}
