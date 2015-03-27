package mst.performance;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import mst.algorithm.KruskalAlgorithm;
import mst.algorithm.MSTFinder;
import mst.algorithm.PrimAlgorithm;
import mst.bean.WeightedGraph;
import mst.random.ErdosRenyiGraphGenerator;

public class MSTAlgorithmPerformanceEvaluator {

	private BufferedWriter writer;

	public void initFilenameWriting(String filename) throws IOException {
		writer = new BufferedWriter(new FileWriter(filename));
	}

	public void write(String text) throws IOException {
		writer.write(text);
	}

	public void close() throws IOException {
		writer.close();
	}

	public void evaluate(int maxN, ErdosRenyiGraphGenerator graphGenerator,
			String filename, MSTFinder... mstAlgorithms)
			throws CloneNotSupportedException, IOException {
		initFilenameWriting(filename);
		for (int n = 4; n <= maxN; n++) {
			final WeightedGraph graph = graphGenerator.generateErdosRenyiGraph(
					n, 0.8f);
			write(n + " ");
			for (MSTFinder algorithm : mstAlgorithms) {
				final double beginning = System.currentTimeMillis();
				algorithm.findMST(graph);
				final double totalTime = System.currentTimeMillis() - beginning;
				write(totalTime + " ");
			}
			write("\n");
		}
		writer.close();
	}

	public static void main(String[] args) throws CloneNotSupportedException,
			IOException {
		final MSTAlgorithmPerformanceEvaluator evaluator = new MSTAlgorithmPerformanceEvaluator();
		final ErdosRenyiGraphGenerator graphGenerator = new ErdosRenyiGraphGenerator();
		evaluator.evaluate(100, graphGenerator, "test.txt", new PrimAlgorithm(),
				new KruskalAlgorithm());
	}

}
