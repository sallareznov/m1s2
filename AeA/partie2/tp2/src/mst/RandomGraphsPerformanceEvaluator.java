package mst;

import java.io.IOException;
import java.util.logging.Logger;

import mst.algorithm.KruskalAlgorithm;
import mst.algorithm.PrimAlgorithm;
import mst.logger.LoggerFactory;
import mst.performance.AlgorithmPerformanceEvaluator;
import mst.random.ErdosRenyiGraphGenerator;

public class RandomGraphsPerformanceEvaluator {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(RandomGraphsPerformanceEvaluator.class);

	private RandomGraphsPerformanceEvaluator() {
	}

	private static void usage() {
		LOGGER.info("java -jar performanceEvaluator.jar <n> <p>");
		LOGGER.info("\tn : le nombre de sommets maximal des graphes generes");
		LOGGER.info("\tp : la probabilité d'existence d'arête entre 2 sommets");
		LOGGER.info("Ce programme générera dans le fichier <output> les résultats");
		LOGGER.info("des temps d'exécution des algorithmes de Prim et de Kruskal sur");
		LOGGER.info("des graphes générés aléatoirement selon le modèle d'Erdos-Renyi");
		LOGGER.info("Pour générer le fichier results.png représentant les résultats");
		LOGGER.info("obtenus sous forme de graphe, exécuter : gnuplot plotter.plot");
		LOGGER.info("Exemple : java -jar performanceEvaluator.jar 20 0.6");
	}

	public static void main(String[] args) throws CloneNotSupportedException,
			IOException, InterruptedException {
		if (args.length < 2) {
			usage();
			return;
		}
		final int n = Integer.parseInt(args[0]);
		final String outputFile = args[1];
		final AlgorithmPerformanceEvaluator evaluator = new AlgorithmPerformanceEvaluator();
		final ErdosRenyiGraphGenerator graphGenerator = new ErdosRenyiGraphGenerator();
		evaluator.evaluate(n, graphGenerator, outputFile,
				new PrimAlgorithm(), new KruskalAlgorithm());
	}

}
