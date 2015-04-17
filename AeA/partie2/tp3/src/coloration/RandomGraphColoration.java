package coloration;

import java.io.IOException;
import java.util.logging.Logger;

import coloration.algorithm.DsaturAlgorithm;
import coloration.algorithm.GreedyAlgorithm;
import coloration.algorithm.VerticesColorationAlgorithm;
import coloration.algorithm.WelshPowellAlgorithm;
import coloration.color.ColoursHolder;
import coloration.logger.LoggerFactory;
import coloration.performance.AlgorithmPerformanceEvaluator;
import coloration.random.ErdosRenyiGraphGenerator;

public class RandomGraphColoration {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RandomGraphColoration.class);
	
	private RandomGraphColoration() {
	}
	
	private static void usage() {
		LOGGER.info("java -jar customGraphColoration.jar <n> <output>");
		LOGGER.info("\tn : le nombre de sommets des graphes generes");
		LOGGER.info("\toutput : le fichier de sortie des résultats");
		LOGGER.info("Ce programme générera 50 graphes aléatoires de n sommets,");
		LOGGER.info("coloriera ces sommets avec Greedy, Welsh-Powell et Dsatur,");
		LOGGER.info("et répertoriera ces resultats dans le fichier output");
	}

	public static void main(String[] args) throws CloneNotSupportedException,
			IOException {
		if (args.length < 1) {
			usage();
		}
		final int n = Integer.parseInt(args[0]); 
		final AlgorithmPerformanceEvaluator evaluator = new AlgorithmPerformanceEvaluator();
		final ErdosRenyiGraphGenerator graphGenerator = new ErdosRenyiGraphGenerator();
		final ColoursHolder coloursHolder = new ColoursHolder();
		final VerticesColorationAlgorithm[] algorithms = {
				new WelshPowellAlgorithm(), new GreedyAlgorithm(),
				new DsaturAlgorithm() };
		evaluator.evaluate(n, graphGenerator, coloursHolder,
				algorithms);
	}

}
 