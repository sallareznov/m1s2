package coloration.algorithm;

import java.util.logging.Logger;

import coloration.bean.WeightedGraph;
import coloration.color.ColoursHolder;
import coloration.logger.LoggerFactory;
import coloration.neighbor.NeighborsManager;

public abstract class AbstractVertexesColorationAlgorithm implements
		VertexesColorationAlgorithm {

	private int nbColoursUsed;
	private long executionTime;
	private static final Logger LOGGER = LoggerFactory
			.getLogger(AbstractVertexesColorationAlgorithm.class);

	@Override
	public void colourGraph(WeightedGraph graph, ColoursHolder coloursHolder)
			throws CloneNotSupportedException {
		final NeighborsManager neighborsManager = new NeighborsManager();
		neighborsManager.initNeighbors(graph);
		coloursHolder.fadeGraph(graph);
		resetNbColoursUsed();
		setExecutionTime(System.currentTimeMillis());
		colourVertexes(graph, neighborsManager, coloursHolder);
	}

	public abstract void colourVertexes(WeightedGraph graph,
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
		LOGGER.info("    - Execution time : " + executionTime);
		LOGGER.info("}");
	}

}
