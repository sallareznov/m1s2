package coloration;

import java.io.IOException;

import coloration.algorithm.GreedyAlgorithm;
import coloration.algorithm.VertexesColorationAlgorithm;
import coloration.algorithm.WelshPowellAlgorithm;
import coloration.bean.WeightedGraph;
import coloration.export.TextualGraphTools;
import coloration.export.ViewableGraphExporter;
import coloration.random.ErdosRenyiGraphGenerator;
import coloration.util.ColoursHolder;

public class Main {

	public static void main(String[] args) throws IOException,
			CloneNotSupportedException {
		final VertexesColorationAlgorithm greedy = new GreedyAlgorithm();
		final VertexesColorationAlgorithm welshPowell = new WelshPowellAlgorithm();
		final TextualGraphTools graphTools = new TextualGraphTools();
		final ErdosRenyiGraphGenerator graphGenerator = new ErdosRenyiGraphGenerator();
		// final WeightedGraph graph =
		// graphGenerator.generateErdosRenyiGraph(20,
		// 0.6f);
		final WeightedGraph graph = graphTools.getGraphFromFile("petersen.grp");
		final ColoursHolder coloursHolder = new ColoursHolder();
		welshPowell.colourGraph(graph, coloursHolder);
		greedy.colourGraph(graph, coloursHolder);
		final ViewableGraphExporter exporter = new ViewableGraphExporter();
		exporter.exportColouredGraphToDotFile(graph, greedy, coloursHolder,
				"colours.properties", "greedy.dot");
		exporter.exportColouredGraphToDotFile(graph, welshPowell,
				coloursHolder, "colours.properties", "welshpowell.dot");
		exporter.exportDotFileToPsFile("greedy.dot", "greedy.ps");
		exporter.exportDotFileToPsFile("welshpowell.dot", "welshpowell.ps");
	}

}
