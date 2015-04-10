package coloration.export;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Logger;

import coloration.algorithm.VertexesColorationAlgorithm;
import coloration.bean.Edge;
import coloration.bean.Vertex;
import coloration.bean.WeightedGraph;
import coloration.color.ColoursHolder;
import coloration.color.IntToColour;
import coloration.logger.LoggerFactory;

public class ViewableGraphExporter {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(ViewableGraphExporter.class);

	public void exportColouredGraphToDotFile(WeightedGraph graph,
			VertexesColorationAlgorithm colorationAlgorithm,
			ColoursHolder coloursHolder, String colorsFilename,
			String dotFilename) throws IOException {
		final IntToColour intToColor = new IntToColour();
		intToColor.initColours(colorsFilename);
		final BufferedWriter reader = new BufferedWriter(new FileWriter(
				dotFilename));
		reader.write("graph {\n");
		reader.write("label=\"Coloured graph\"\n");
		for (final Vertex vertex : graph.getVertexes()) {
			final Integer intColour = coloursHolder.getColour(vertex);
			final String stringColour = intToColor.getColourString(intColour);
			// final String stringColour = IntToColour.encodeColor(IntToColour
			// .hashColor(intColour));
			reader.write(vertex + "[label=\"" + vertex
					+ "\", style=\"filled\", color=\"black\", fillcolor=\""
					+ stringColour + "\"]\n");
		}
		for (final Edge edge : graph.getEdges()) {
			final Vertex vertex1 = edge.getVertex1();
			final Vertex vertex2 = edge.getVertex2();
			reader.write(vertex1 + " -- " + vertex2 + "\n");
		}
		reader.write("}");
		reader.close();
	}

	public void exportDotFileToPsFile(String dotFilename, String psFilename)
			throws IOException {
		LOGGER.info("> Exporting " + dotFilename
				+ " to viewable PostScript file");
		final String command = "dot -Tps " + dotFilename;
		final Process process = Runtime.getRuntime().exec(command);
		final BufferedReader reader = new BufferedReader(new InputStreamReader(
				process.getInputStream()));
		final BufferedWriter writer = new BufferedWriter(new FileWriter(
				psFilename));
		int c = -1;
		while ((c = reader.read()) != -1) {
			writer.write(c);
		}
		reader.close();
		writer.close();
		LOGGER.info("> Finished ! Output is " + psFilename);
	}

}