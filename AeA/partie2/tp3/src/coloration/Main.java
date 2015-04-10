package coloration;

import java.io.IOException;

import coloration.algorithm.DsaturAlgorithm;
import coloration.algorithm.GreedyAlgorithm;
import coloration.algorithm.VertexesColorationAlgorithm;
import coloration.algorithm.WelshPowellAlgorithm;
import coloration.bean.WeightedGraph;
import coloration.color.ColoursHolder;
import coloration.export.TextualGraphTools;
import coloration.export.ViewableGraphExporter;
import coloration.random.ErdosRenyiGraphGenerator;

public class Main {

	public static void main(String[] args) throws IOException,
			CloneNotSupportedException {
		int n = 1000;
		float p = 0.8f;
		final VertexesColorationAlgorithm greedy = new GreedyAlgorithm();
		final VertexesColorationAlgorithm welshPowell = new WelshPowellAlgorithm();
		final VertexesColorationAlgorithm dsatur = new DsaturAlgorithm();
		final TextualGraphTools graphTools = new TextualGraphTools();
		final ErdosRenyiGraphGenerator graphGenerator = new ErdosRenyiGraphGenerator();
//		final WeightedGraph graph = graphGenerator.generateErdosRenyiGraph(10,
//				0.8f);
		final WeightedGraph graph =
		graphTools.getGraphFromFile("ex2.grp");
		final ColoursHolder coloursHolder = new ColoursHolder();
		final ViewableGraphExporter exporter = new ViewableGraphExporter();
		System.out.println("n = " + graph.getSize());
		welshPowell.colourGraph(graph, coloursHolder);
		welshPowell.printMeasures();
		exporter.exportColouredGraphToDotFile(graph, welshPowell,
				coloursHolder, "colours.properties", "welshpowell.dot");
		greedy.colourGraph(graph, coloursHolder);
		greedy.printMeasures();
		exporter.exportColouredGraphToDotFile(graph, greedy, coloursHolder,
				"colours.properties", "greedy.dot");
		dsatur.colourGraph(graph, coloursHolder);
		dsatur.printMeasures();
		exporter.exportColouredGraphToDotFile(graph, dsatur, coloursHolder,
				"colours.properties", "dsatur.dot");
		exporter.exportDotFileToPsFile("greedy.dot", "greedy.ps");
		exporter.exportDotFileToPsFile("welshpowell.dot", "welshpowell.ps");
		exporter.exportDotFileToPsFile("dsatur.dot", "dsatur.ps");
	}

}
