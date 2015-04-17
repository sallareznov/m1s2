package coloration;

import java.io.IOException;
import java.util.logging.Logger;

import coloration.algorithm.DsaturAlgorithm;
import coloration.algorithm.GreedyAlgorithm;
import coloration.algorithm.VerticesColorationAlgorithm;
import coloration.algorithm.WelshPowellAlgorithm;
import coloration.bean.WeightedGraph;
import coloration.color.ColoursHolder;
import coloration.export.TextualGraphTools;
import coloration.export.ViewableGraphExporter;
import coloration.logger.LoggerFactory;

public class CustomGraphColoration {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(CustomGraphColoration.class);

	private CustomGraphColoration() {
	}

	private static void usage() {
		LOGGER.info("java -jar customGraphColoration.jar <filename>");
		LOGGER.info("\tfilename : le nom du fichier comportant le graphe a etudier");
		LOGGER.info("Ce programme construira le graphe spécifié dans le fichier,");
		LOGGER.info("coloriera ses sommets avec Greedy, Welsh-Powell et Dsatur,");
		LOGGER.info("et répertoriera ces resultats dans les fichiers greedy.{dot, ps}");
		LOGGER.info("welshpowell.{dot, ps} et dsatur.{dot, ps}");
	}

	public static void main(String[] args) throws IOException,
			CloneNotSupportedException {
		if (args.length < 1) {
			usage();
			return;
		}
		final TextualGraphTools graphTools = new TextualGraphTools();
		final WeightedGraph graph = graphTools.getGraphFromFile(args[0]);
		final ColoursHolder coloursHolder = new ColoursHolder();
		final ViewableGraphExporter exporter = new ViewableGraphExporter();
		final VerticesColorationAlgorithm greedy = new GreedyAlgorithm();
		final VerticesColorationAlgorithm welshPowell = new WelshPowellAlgorithm();
		final VerticesColorationAlgorithm dsatur = new DsaturAlgorithm();
		final String coloursFilename = "colours.properties";
		greedy.colourGraph(graph, coloursHolder);
		exporter.exportColouredGraphToDotFile(graph, coloursHolder,
				coloursFilename, "greedy.dot");
		welshPowell.colourGraph(graph, coloursHolder);
		exporter.exportColouredGraphToDotFile(graph, coloursHolder,
				coloursFilename, "welshpowell.dot");
		dsatur.colourGraph(graph, coloursHolder);
		exporter.exportColouredGraphToDotFile(graph, coloursHolder,
				coloursFilename, "dsatur.dot");
		greedy.printMeasures();
		welshPowell.printMeasures();
		dsatur.printMeasures();

		exporter.exportDotFileToPsFile("greedy.dot", "greedy.ps");
		exporter.exportDotFileToPsFile("welshpowell.dot", "welshpowell.ps");
		exporter.exportDotFileToPsFile("dsatur.dot", "dsatur.ps");
	}

}
