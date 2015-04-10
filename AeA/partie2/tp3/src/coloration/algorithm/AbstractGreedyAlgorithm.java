package coloration.algorithm;

import coloration.bean.Vertex;
import coloration.color.ColoursHolder;
import coloration.neighbor.NeighborsManager;

public abstract class AbstractGreedyAlgorithm extends
		AbstractVertexesColorationAlgorithm {

	public void setSmallestNotUsedColour(Vertex vertex,
			NeighborsManager neighborsManager, ColoursHolder coloursHolder) {
		int smallestColour = Integer.MAX_VALUE;
		for (final Vertex neighbor : neighborsManager.getNeighbors(vertex)) {
			final Integer color = coloursHolder.getColour(neighbor);
			if (color != -1)
				smallestColour = Math.min(smallestColour, color);
		}
		if (smallestColour > 0) {
			coloursHolder.colorVertex(vertex, 0);
		} else {
			for (int i = 0; i <= getNbColoursUsed() + 1; i++) {
				//System.out.println("here");
				boolean usedColour = false;
				for (final Vertex neighbor : neighborsManager
						.getNeighbors(vertex)) {
					//System.out.println("neighbor = " + neighbor);
					if (coloursHolder.isColored(neighbor)
							&& coloursHolder.getColour(neighbor) == i) {
						//System.out.println(neighbor + " : used");
						usedColour = true;
						break;
					}
				}
				if (!usedColour) {
					coloursHolder.colorVertex(vertex, i);
					break;
				}
			}
			if (!coloursHolder.isColored(vertex)) {
				coloursHolder.colorVertex(vertex, getNbColoursUsed() + 2);
			}
		}
	}

}
