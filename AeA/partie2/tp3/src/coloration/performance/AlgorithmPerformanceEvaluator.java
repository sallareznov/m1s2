package coloration.performance;

import java.io.IOException;
import java.util.logging.Logger;

import coloration.algorithm.VerticesColorationAlgorithm;
import coloration.bean.WeightedGraph;
import coloration.color.ColoursHolder;
import coloration.logger.LoggerFactory;
import coloration.random.ErdosRenyiGraphGenerator;

public class AlgorithmPerformanceEvaluator {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(AlgorithmPerformanceEvaluator.class);

	public void evaluate(int n, ErdosRenyiGraphGenerator graphGenerator,
			String filename, ColoursHolder coloursHolder, VerticesColorationAlgorithm... algorithms) throws CloneNotSupportedException, IOException {
		LOGGER.info("> Performance evaluation in progress...");
		float p = 0.1f;
		for (int i = 1; i <= 50; i++) {
				final WeightedGraph graph = graphGenerator
					.generateErdosRenyiGraph(n, p);
				LOGGER.info("---> Graph n°" + i);
				LOGGER.info("n = " + n + " | p = " + p);
				for (VerticesColorationAlgorithm algorithm : algorithms) {
					algorithm.colourGraph(graph, coloursHolder);
					algorithm.printMeasures();
				}
				LOGGER.info("");
				p = (p + 0.2f) % 1;
		}
		LOGGER.info("> Performance evaluation terminated");
	}

}
