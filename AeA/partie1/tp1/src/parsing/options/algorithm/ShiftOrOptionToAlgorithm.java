package parsing.options.algorithm;

import manager.Behavior;
import algorithms.ShiftOrAlgorithm;

public class ShiftOrOptionToAlgorithm implements Behavior<OptionToAlgorithmParameters, OptionToAlgorithmResult> {

	@Override
	public boolean accept(OptionToAlgorithmParameters parameters) {
		final String option = parameters.getOption();
		return "-so".equals(option);
	}

	@Override
	public OptionToAlgorithmResult execute(
			OptionToAlgorithmParameters parameters) {
		return new OptionToAlgorithmResult(new ShiftOrAlgorithm());
	}

}
