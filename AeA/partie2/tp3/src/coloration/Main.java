package coloration;

import java.io.IOException;

import coloration.algorithm.WelshPowellAlgorithm;
import coloration.bean.WeightedGraph;
import coloration.export.TextualGraphTools;
import coloration.export.ViewableGraphExporter;
import coloration.random.ErdosRenyiGraphGenerator;
import coloration.util.ColoursHolder;

public class Main {

	public static void main(String[] args) throws IOException,
			CloneNotSupportedException {
		final WelshPowellAlgorithm algo = new WelshPowellAlgorithm();
		final TextualGraphTools graphTools = new TextualGraphTools();
		final ErdosRenyiGraphGenerator graphGenerator = new ErdosRenyiGraphGenerator();
		//final WeightedGraph graph = graphGenerator.generateErdosRenyiGraph(20,
			//	0.6f);
		final WeightedGraph graph = graphTools.getGraphFromFile("petersen.grp");
		final ColoursHolder coloursHolder = new ColoursHolder();
		algo.colourGraph(graph, coloursHolder);
		final ViewableGraphExporter exporter = new ViewableGraphExporter();
		exporter.exportColouredGraphToDotFile(graph, algo, coloursHolder,
				"colours.properties", "graph.dot");
		exporter.exportDotFileToPsFile("graph.dot", "graph.ps");
	}

}
