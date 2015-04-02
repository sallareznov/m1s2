package mst.performance;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import mst.algorithm.MinimumSpanningTreeFinder;
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
			String filename, MinimumSpanningTreeFinder... mstAlgorithms)
			throws CloneNotSupportedException, IOException {
		initFilenameWriting(filename);
		for (int n = 4; n <= maxN; n++) {
			final WeightedGraph graph = graphGenerator.generateErdosRenyiGraph(
					n, 0.8f);
			write(n + " ");
			for (MinimumSpanningTreeFinder algorithm : mstAlgorithms) {
				algorithm.findTree(graph);
				final long executionTime = algorithm.getExecutionTime();
				write(executionTime + " ");
			}
			write("\n");
		}
		writer.close();
	}

}
