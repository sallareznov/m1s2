package coloration.algorithm;

import java.util.logging.Logger;

import coloration.bean.WeightedGraph;
import coloration.color.ColoursHolder;
import coloration.logger.LoggerFactory;
import coloration.neighbor.NeighborsManager;

public abstract class AbstractVerticesColorationAlgorithm implements
		VerticesColorationAlgorithm {

	private int nbColoursUsed;
	private long executionTime;
	private static final Logger LOGGER = LoggerFactory
			.getLogger(AbstractVerticesColorationAlgorithm.class);

	@Override
	public void colourGraph(WeightedGraph graph, ColoursHolder coloursHolder)
			throws CloneNotSupportedException {
		final NeighborsManager neighborsManager = new NeighborsManager();
		neighborsManager.initNeighbors(graph);
		coloursHolder.fadeGraph(graph);
		resetNbColoursUsed();
		setExecutionTime(System.currentTimeMillis());
		colourVertices(graph, neighborsManager, coloursHolder);
	}

	public abstract void colourVertices(WeightedGraph graph,
			NeighborsManager neighborsManager, ColoursHolder coloursHolder)
			throws CloneNotSupportedException;

	@Override
	public int getNbColoursUsed() {
		return nbColoursUsed;
	}

	@Override
	public void incrementNbColoursUsed() {
		nbColoursUsed++;
	}

	@Override
	public void resetNbColoursUsed() {
		nbColoursUsed = 0;
	}

	@Override
	public long getExecutionTime() {
		return executionTime;
	}

	@Override
	public void setExecutionTime(long executionTime) {
		this.executionTime = executionTime;
	}

	@Override
	public void updateExecutionTime(long endTime) {
		executionTime = endTime - executionTime;
	}

	@Override
	public void printMeasures() {
		LOGGER.info(toString() + " : {");
		LOGGER.info("    - Number of colours used : " + nbColoursUsed);
		LOGGER.info("    - Execution time : " + executionTime + " milliseconds");
		LOGGER.info("}");
	}

}
