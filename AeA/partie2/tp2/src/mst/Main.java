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
		graph.addEdge(a, b, 7);
		graph.addEdge(a, d, 5);
		graph.addEdge(b, c, 8);
		graph.addEdge(b, d, 9);
		graph.addEdge(b, e, 7);
		graph.addEdge(c, e, 5);
		graph.addEdge(d, e, 15);
		graph.addEdge(d, f, 6);
		graph.addEdge(e, f, 8);
		graph.addEdge(e, g, 9);
		graph.addEdge(f, g, 11);
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
