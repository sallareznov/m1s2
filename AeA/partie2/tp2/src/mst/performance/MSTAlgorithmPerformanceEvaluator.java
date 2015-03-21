package mst.performance;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import mst.algorithm.MSTFinder;
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
			float p = 0.2f;
			while (p < 1) {
				final WeightedGraph graph = graphGenerator
						.generateErdosRenyiGraph(n, p);
				write(n + " ");
				for (MSTFinder algorithm : mstAlgorithms) {
					final double beginning = System.currentTimeMillis();
					algorithm.findMST(graph);
					final double totalTime = System.currentTimeMillis() - beginning;
					write(totalTime + " ");
				}
				write("\n");
				p += 0.2f;
			}
		}
		writer.close();
	}

}
