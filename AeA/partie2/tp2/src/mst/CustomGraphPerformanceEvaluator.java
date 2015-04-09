package mst;

import java.io.IOException;
import java.util.logging.Logger;

import mst.algorithm.KruskalAlgorithm;
import mst.algorithm.MinimumSpanningTreeFinder;
import mst.algorithm.PrimAlgorithm;
import mst.bean.WeightedGraph;
import mst.export.ViewableGraphExporter;
import mst.export.TextualGraphTools;
import mst.logger.LoggerFactory;

public class CustomGraphPerformanceEvaluator {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(CustomGraphPerformanceEvaluator.class);

	private CustomGraphPerformanceEvaluator() {
	}

	private static void usage() {
		LOGGER.info("java -jar customPE.jar <filename>");
		LOGGER.info("\tfilename : le nom du fichier comportant le graphe a etudier");
		LOGGER.info("Ce programme construira le graphe spécifié dans le fichier,");
		LOGGER.info("recherchera un arbre couvrant minimal avec Prim et Kruskal,");
		LOGGER.info("et répertoriera ces resultats dans les fichiers prim.dot et");
		LOGGER.info("kruskal.dot");
		LOGGER.info("Pour visualiser les résultats, dot -Tps file.dot > file.ps");
	}

	public static void main(String[] args) throws IOException,
			CloneNotSupportedException {
		if (args.length < 1) {
			usage();
			return;
		}
		final String filename = args[0];
		final TextualGraphTools graphTools = new TextualGraphTools();
		final WeightedGraph graph = graphTools.getGraphFromFile(filename);
		final ViewableGraphExporter graphExporter = new ViewableGraphExporter();
		final MinimumSpanningTreeFinder primTreeFinder = new PrimAlgorithm();
		final MinimumSpanningTreeFinder kruskalTreeFinder = new KruskalAlgorithm();
		final WeightedGraph primMst = primTreeFinder.findTree(graph);
		final WeightedGraph kruskalMst = new KruskalAlgorithm().findTree(graph);
		graphExporter.exportMstToDotFile("prim.dot", primTreeFinder, graph,
				primMst);
		graphExporter.exportMstToDotFile("kruskal.dot", kruskalTreeFinder,
				graph, kruskalMst);
		graphExporter.exportDotFileToPsFile("prim.dot", "prim.ps");
		graphExporter.exportDotFileToPsFile("kruskal.dot", "kruskal.ps");
	}

}
