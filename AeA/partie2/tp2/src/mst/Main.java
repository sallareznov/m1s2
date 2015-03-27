package mst;

import java.io.IOException;

import mst.algorithm.KruskalAlgorithm;
import mst.algorithm.MSTFinder;
import mst.algorithm.PrimAlgorithm;
import mst.bean.Vertex;
import mst.bean.WeightedGraph;
import mst.export.GraphExporter;

public class Main {
	
	private Main() {
	}
	
	public static void main(String[] args) throws CloneNotSupportedException, IOException, InterruptedException {
		final WeightedGraph graph = new WeightedGraph();
		final Vertex a = new Vertex("A");
		final Vertex b = new Vertex("B");
		final Vertex c = new Vertex("C");
		final Vertex d = new Vertex("D");
		final Vertex e = new Vertex("E");
		final Vertex f = new Vertex("F");
		final Vertex g = new Vertex("G");
		graph.addEdge(a, b, 4);
		graph.addEdge(a, f, 3);
		graph.addEdge(a, g, 5);
		graph.addEdge(b, c, 3);
		graph.addEdge(b, e, 1);
		graph.addEdge(b, f, 2);
		graph.addEdge(c, d, 6);
		graph.addEdge(c, e, 3);
		graph.addEdge(d, e, 4);
		graph.addEdge(d, f, 5);
		graph.addEdge(d, g, 3);
		graph.addEdge(e, f, 3);
		graph.addEdge(f, g, 4);
		final GraphExporter graphExporter = new GraphExporter();
		final MSTFinder algoKruskal = new KruskalAlgorithm();
		final WeightedGraph mstKruskal = algoKruskal.findMST(graph);
		final String kruskalDotFilename = "kruskal.dot";
		//final String kruskalPsFilename = "kruskal.ps";
		graphExporter.exportMstToDotFile(kruskalDotFilename, algoKruskal, graph, mstKruskal);
		//mstPrinter.makeViewable(kruskalDotFilename, kruskalPsFilename);
		//mstPrinter.viewMST(kruskalPsFilename);
		final MSTFinder algoPrim = new PrimAlgorithm();
		final WeightedGraph mstPrim = algoPrim.findMST(graph);
		final String primDotFilename = "prim.dot";
		//final String primPsFilename = "prim.ps";
		graphExporter.exportMstToDotFile(primDotFilename, algoPrim, graph, mstPrim);
		//mstPrinter.makeViewable(primDotFilename, primPsFilename);
		//mstPrinter.viewMST(primPsFilename);
	}

}
