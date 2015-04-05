package mst.performance;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Logger;

import mst.algorithm.MinimumSpanningTreeFinder;
import mst.bean.WeightedGraph;
import mst.logger.LoggerFactory;
import mst.random.ErdosRenyiGraphGenerator;

public class AlgorithmPerformanceEvaluator {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(AlgorithmPerformanceEvaluator.class);
	private BufferedWriter[] writers;

	public void initFilenameWriting(String filename) throws IOException {
		final int extensionIndex = filename.lastIndexOf('.');
		final String filenameWithoutExtension = filename.substring(0,
				extensionIndex);
		final String extension = filename.substring(extensionIndex,
				filename.length());
		writers = new BufferedWriter[10];
		for (int i = 0; i < 10; i++) {
			writers[i] = new BufferedWriter(new FileWriter(
					filenameWithoutExtension + "_p" + (i + 1) + extension));
		}
	}

	public void write(int index, String text) throws IOException {
		writers[index].write(text);
	}

	public void closeWriters() throws IOException {
		for (final BufferedWriter writer : writers) {
			writer.close();
		}
	}

	public void evaluate(int maxN, ErdosRenyiGraphGenerator graphGenerator,
			String filename, MinimumSpanningTreeFinder... mstAlgorithms)
			throws CloneNotSupportedException, IOException {
		LOGGER.info("> Performance evaluation in progress...");
		initFilenameWriting(filename);
		for (int n = 4; n <= maxN; n++) {
			float p = 0.1f;
			for (int index = 0 ; index < 10 ; index++) {
				final WeightedGraph graph = graphGenerator
						.generateErdosRenyiGraph(n, p);
				write(index, n + " ");
				for (MinimumSpanningTreeFinder algorithm : mstAlgorithms) {
					algorithm.findTree(graph);
					final long executionTime = algorithm.getExecutionTime();
					write(index, executionTime + " ");
				}
				write(index, "\n");
				p += 0.1f;
			}
		}
		LOGGER.info("> Performance evaluation terminated");
		LOGGER.info("You can view results in the file " + filename);
		closeWriters();
	}

}
