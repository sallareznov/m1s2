package mst.export;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Logger;

import mst.algorithm.MinimumSpanningTreeFinder;
import mst.bean.Edge;
import mst.bean.Vertex;
import mst.bean.WeightedGraph;
import mst.logger.LoggerFactory;

public class ViewableGraphExporter {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(ViewableGraphExporter.class);

	public void exportSimpleGraphToDotFile(WeightedGraph graph,
			String dotFilename) throws IOException {
		final BufferedWriter reader = new BufferedWriter(new FileWriter(
				dotFilename));
		reader.write("graph {\n");
		reader.write("label=\"Random graph\"\n");
		for (final Vertex vertex : graph.getVertexes()) {
			reader.write(vertex + "\n");
		}
		for (final Edge edge : graph.getEdges()) {
			final Vertex vertex1 = edge.getVertex1();
			final Vertex vertex2 = edge.getVertex2();
			reader.write(vertex1 + "  " + vertex2);
			reader.write(" [label=\"" + edge.getWeight() + "\"]\n");
		}
		reader.write("}");
		reader.close();
	}

	public void exportMstToDotFile(String dotFilename,
			MinimumSpanningTreeFinder finder, WeightedGraph graph,
			WeightedGraph mst) throws IOException {
		final BufferedWriter reader = new BufferedWriter(new FileWriter(
				dotFilename));
		LOGGER.info("> Writing " + finder + " to dotfile ");
		reader.write("graph {\n");
		reader.write("label=\"" + finder + "\"\n");
		for (final Vertex vertex : graph.getVertexes()) {
			reader.write(vertex + " [color=blue]\n");
		}
		for (final Edge edge : graph.getEdges()) {
			String edgeProperties = "[label=\"" + edge.getWeight() + "\"";
			final Vertex vertex1 = edge.getVertex1();
			final Vertex vertex2 = edge.getVertex2();
			reader.write(vertex1 + "--" + vertex2);
			if (mst.containsEdge(edge)) {
				edgeProperties += ",color=green]";
			} else {
				edgeProperties += "]";
			}
			reader.write(edgeProperties + "\n");
		}
		reader.write("}");
		reader.close();
		LOGGER.info("> Finished ! Output is " + dotFilename);
	}

	public void exportDotFileToPsFile(String dotFilename, String psFilename)
			throws IOException {
		LOGGER.info("> Exporting " + dotFilename
				+ " to viewable PostScript file");
		final String command = "dot -Tps " + dotFilename;
		final Process process = Runtime.getRuntime().exec(command);
		final BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
		final BufferedWriter writer = new BufferedWriter(new FileWriter(psFilename));
		int c = -1;
		while ((c = reader.read()) != -1) {
			writer.write(c);
		}
		reader.close();
		writer.close();
		LOGGER.info("> Finished ! Output is " + psFilename);
	}

}