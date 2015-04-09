package coloration.random;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import coloration.bean.Edge;
import coloration.bean.Vertex;
import coloration.bean.WeightedGraph;

public class ErdosRenyiGraphGenerator {
	
	public WeightedGraph generateErdosRenyiGraph(int n, float p) {
		final Vertex[] vertexes = new Vertex[n];
		for (int i = 0 ; i < n ; i++) {
			vertexes[i] = new Vertex((i + 1) + "");
		}
		final List<Edge> edges = new LinkedList<Edge>();
		final Random random = new Random();
		for (int i = 0 ; i < n ; i++) {
			for (int j = 0 ; j < n ; j++) {
				final Vertex vertex1 = vertexes[i];
				final Vertex vertex2 = vertexes[j];
				final float edgeProba = random.nextFloat();
				final boolean condition1 = !vertex1.equals(vertex2);
				final boolean condition2 = !edges.contains(new Edge(vertex1, vertex2));
				final boolean condition3 = edgeProba < p;
				if (condition1 && condition2 && condition3)
					edges.add(new Edge(vertexes[i], vertexes[j]));
			}
		}
		final Set<Vertex> setVertexes = new HashSet<Vertex>(Arrays.asList(vertexes)); 
		return new WeightedGraph(setVertexes, edges);
	}

}
