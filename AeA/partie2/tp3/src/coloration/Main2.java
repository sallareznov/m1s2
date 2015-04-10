package coloration;

import java.io.IOException;

import coloration.algorithm.DsaturAlgorithm;
import coloration.algorithm.GreedyAlgorithm;
import coloration.algorithm.VerticesColorationAlgorithm;
import coloration.algorithm.WelshPowellAlgorithm;
import coloration.color.ColoursHolder;
import coloration.performance.AlgorithmPerformanceEvaluator;
import coloration.random.ErdosRenyiGraphGenerator;

public class Main2 {

	public static void main(String[] args) throws CloneNotSupportedException,
			IOException {
		final AlgorithmPerformanceEvaluator evaluator = new AlgorithmPerformanceEvaluator();
		final ErdosRenyiGraphGenerator graphGenerator = new ErdosRenyiGraphGenerator();
		final ColoursHolder coloursHolder = new ColoursHolder();
		final VerticesColorationAlgorithm[] algorithms = {
				new WelshPowellAlgorithm(), new GreedyAlgorithm(),
				new DsaturAlgorithm() };
		evaluator.evaluate(1000, graphGenerator, "output.txt", coloursHolder,
				algorithms);
	}

}
 