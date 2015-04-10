package mst.random;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import mst.bean.Edge;
import mst.bean.Vertex;
import mst.bean.WeightedGraph;

public class ErdosRenyiGraphGenerator {
	
	public WeightedGraph generateErdosRenyiGraph(int n, float p) {
		final Vertex[] vertices = new Vertex[n];
		for (int i = 0 ; i < n ; i++) {
			vertices[i] = new Vertex((i + 1) + "");
		}
		final List<Edge> edges = new LinkedList<Edge>();
		final Random random = new Random();
		for (int i = 0 ; i < n ; i++) {
			for (int j = i + 1 ; j < n ; j++) {
				final int randomWeight = random.nextInt(Integer.MAX_VALUE);
				if (random.nextFloat() < p)
					edges.add(new Edge(vertices[i], vertices[j], randomWeight));
			}
		}
		final Set<Vertex> setVertices = new HashSet<Vertex>(Arrays.asList(vertices)); 
		return new WeightedGraph(setVertices, edges);
	}

}
