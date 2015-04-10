package coloration.sort;

import java.util.Comparator;
import java.util.List;

import coloration.bean.Vertex;
import coloration.neighbor.NeighborsManager;

public class VerticesComparator implements Comparator<Vertex> {
	
	private NeighborsManager neighborsManager;
	
	public VerticesComparator(NeighborsManager neighborsManager) {
		this.neighborsManager = neighborsManager;
	}

	@Override
	public int compare(Vertex vertex1, Vertex vertex2) {
		final List<Vertex> vertex1Neighbors = neighborsManager.getNeighbors(vertex1);
		final List<Vertex> vertex2Neighbors = neighborsManager.getNeighbors(vertex2);
		return vertex2Neighbors.size() - vertex1Neighbors.size();
	}

}
