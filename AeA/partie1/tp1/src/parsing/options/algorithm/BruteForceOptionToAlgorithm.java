package parsing.options.algorithm;

import manager.Behavior;
import algorithms.BruteForceAlgorithm;

public class BruteForceOptionToAlgorithm implements Behavior<OptionToAlgorithmParameters, OptionToAlgorithmResult> {

	@Override
	public boolean accept(OptionToAlgorithmParameters parameters) {
		final String option = parameters.getOption();
		return "-bf".equals(option);
	}

	@Override
	public OptionToAlgorithmResult execute(OptionToAlgorithmParameters parameters) {
		return new OptionToAlgorithmResult(new BruteForceAlgorithm());
	}

}
