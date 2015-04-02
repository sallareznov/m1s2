package mst;

import java.io.IOException;

import mst.algorithm.KruskalAlgorithm;
import mst.algorithm.PrimAlgorithm;
import mst.performance.MSTAlgorithmPerformanceEvaluator;
import mst.random.ErdosRenyiGraphGenerator;

public class Main {

	private Main() {
	}

	public static void main(String[] args) throws CloneNotSupportedException,
			IOException, InterruptedException {
		final MSTAlgorithmPerformanceEvaluator evaluator = new MSTAlgorithmPerformanceEvaluator();
		final ErdosRenyiGraphGenerator graphGenerator = new ErdosRenyiGraphGenerator();
		evaluator.evaluate(80, graphGenerator, "output.txt", new PrimAlgorithm(),
				new KruskalAlgorithm());
	}

}
