package mst;

import java.io.IOException;

public class Main {
	
	public static void main(String[] args) throws CloneNotSupportedException, IOException {
		final WeightedGraph graph = new WeightedGraph();
		final Vertex a = new Vertex("A");
		final Vertex b = new Vertex("B");
		final Vertex c = new Vertex("C");
		final Vertex d = new Vertex("D");
		final Vertex e = new Vertex("E");
		final Vertex f = new Vertex("F");
		final Vertex g = new Vertex("G");
		graph.addRidge(a, b, 7);
		graph.addRidge(a, d, 5);
		graph.addRidge(b, c, 8);
		graph.addRidge(b, d, 9);
		graph.addRidge(b, e, 7);
		graph.addRidge(c, e, 5);
		graph.addRidge(d, e, 15);
		graph.addRidge(d, f, 6);
		graph.addRidge(e, f, 8);
		graph.addRidge(e, g, 9);
		graph.addRidge(f, g, 11);
		final MSTPrinter mstPrinter = new MSTPrinter();
		final MSTFinder algoKruskal = new KruskalAlgorithm();
		final WeightedGraph mstKruskal = algoKruskal.findMST(graph);
		mstPrinter.printMST("kruskal.dot", graph, mstKruskal);
		final MSTFinder algoPrim = new PrimAlgorithm();
		final WeightedGraph mstPrim = algoPrim.findMST(graph);
		mstPrinter.printMST("prim.dot", graph, mstPrim);
	}

}
