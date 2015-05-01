package mst;

import java.io.IOException;
import java.util.logging.Logger;

import mst.algorithm.KruskalAlgorithm;
import mst.algorithm.MinimumSpanningTreeFinder;
import mst.algorithm.PrimAlgorithm;
import mst.bean.WeightedGraph;
import mst.logger.LoggerFactory;
import mst.random.ErdosRenyiGraphGenerator;

public class RandomGraphSolver {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(RandomGraphSolver.class);

	private RandomGraphSolver() {
	}

	private static void usage() {
		LOGGER.info("java -jar randomGraphSolver.jar <n> <p>");
		LOGGER.info("\tn : le nombre de sommets maximal des graphes generes");
		LOGGER.info("\tp : la probabilité d'existence d'arête entre 2 sommets");
		LOGGER.info("Ce programme générera dans le fichier <output> les résultats");
		LOGGER.info("des temps d'exécution des algorithmes de Prim et de Kruskal sur");
		LOGGER.info("un graphe généré aléatoirement selon le modèle d'Erdos-Renyi");
		LOGGER.info("Exemple : java -jar randomGraphSolver.jar 100 0.7");
	}

	public static void main(String[] args) throws IOException,
			CloneNotSupportedException {
		if (args.length < 2) {
			usage();
			return;
		}
		final int n = Integer.parseInt(args[0]);
		final float p = Float.parseFloat(args[1]);
		final ErdosRenyiGraphGenerator graphGenerator = new ErdosRenyiGraphGenerator();
		final WeightedGraph graph = graphGenerator.generateErdosRenyiGraph(n, p);
		final MinimumSpanningTreeFinder primTreeFinder = new PrimAlgorithm();
		final MinimumSpanningTreeFinder kruskalTreeFinder = new KruskalAlgorithm();
		final WeightedGraph primMst = primTreeFinder.findTree(graph);
		final WeightedGraph kruskalMst = kruskalTreeFinder.findTree(graph);
		LOGGER.info("- Nombre de sommets :");
		LOGGER.info("\tPrim : " + primMst.getTotalWeight() + " sommets.");
		LOGGER.info("\tKruskal : " + kruskalMst.getTotalWeight() + " sommets.");
		LOGGER.info("- Temps d'exécution :");
		LOGGER.info("\tPrim : " + primTreeFinder.getExecutionTime() + " millisecondes.");
		LOGGER.info("\tKruskal : " + kruskalTreeFinder.getExecutionTime() + " millisecondes.");
	}

}
