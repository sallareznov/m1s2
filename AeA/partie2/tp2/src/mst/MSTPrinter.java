package mst;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class MSTPrinter {
	
	public void printMST(String filename, WeightedGraph graph, WeightedGraph mst) throws IOException {
		final BufferedWriter reader = new BufferedWriter(new FileWriter(filename));
		reader.write("graph {\n");
		for (final Vertex vertex : graph.getVertexes()) {
			reader.write(vertex + " [color=blue]\n");
		}
		for (final Ridge ridge : graph.getRidges()) {
			final Vertex vertex1 = ridge.getVertex1();
			final Vertex vertex2 = ridge.getVertex2();
			reader.write(vertex1 + " -- " + vertex2);
			if (mst.containsRidge(ridge)) {
				reader.write(" [color=green, label=\"" + ridge.getWeight() + "\"]");
			}
			reader.write("\n");
		}
		reader.write("}");
		reader.close();
	}

}
