package coloration.algorithm;

import coloration.bean.WeightedGraph;
import coloration.color.ColoursHolder;

public interface VertexesColorationAlgorithm {

	void colourGraph(WeightedGraph graph,
			ColoursHolder coloursHolder)
			throws CloneNotSupportedException;
	
	int getNbColoursUsed();

	void incrementNbColoursUsed();
	
	void resetNbColoursUsed();
	
	long getExecutionTime();
	
	void setExecutionTime(long executionTime);
	
	void updateExecutionTime(long endTime);
	
	void printMeasures();
	
}
