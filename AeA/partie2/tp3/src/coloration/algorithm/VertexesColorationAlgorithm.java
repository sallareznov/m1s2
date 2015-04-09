package coloration.algorithm;

import coloration.bean.WeightedGraph;
import coloration.util.ColoursHolder;

public interface VertexesColorationAlgorithm {

	void colourGraph(WeightedGraph graph,
			ColoursHolder coloursHolder)
			throws CloneNotSupportedException;
	
	int getNbUsedColours();

	void incrementNbUsedColours();
	
	void resetNbUsedColours();
	
	long getExecutionTime();
	
	void updateExecutionTime(long endTime);
}
