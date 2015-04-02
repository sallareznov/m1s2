package mst;

import java.io.IOException;

import mst.algorithm.KruskalAlgorithm;
import mst.algorithm.MinimumSpanningTreeFinder;
import mst.algorithm.PrimAlgorithm;
import mst.bean.WeightedGraph;
import mst.export.GraphExporter;
import mst.export.GraphTools;

public class CustomPerformanceEvaluator {
	
	private CustomPerformanceEvaluator() {
	}
	
	public static void main(String[] args) throws IOException, CloneNotSupportedException {
		final String filename = args[0];
		final GraphTools graphTools = new GraphTools();
		final WeightedGraph graph = graphTools.getGraphFromFile(filename);
		final GraphExporter graphExporter = new GraphExporter();
		final MinimumSpanningTreeFinder primTreeFinder = new PrimAlgorithm();
		final MinimumSpanningTreeFinder kruskalTreeFinder = new KruskalAlgorithm();
		final WeightedGraph primMst = primTreeFinder.findTree(graph);
		final WeightedGraph kruskalMst = new KruskalAlgorithm().findTree(graph);
		graphExporter.exportMstToDotFile("prim.dot", primTreeFinder, graph, primMst);
		graphExporter.exportMstToDotFile("kruskal.dot", kruskalTreeFinder, graph, kruskalMst);
		graphExporter.exportDotFileToPsFile("prim.dot", "prim.ps");
	}

}
