package parsing.options.algorithm;

import manager.Behavior;
import algorithms.BoyerMooreAlgorithm;

public class BoyerMooreOptionToAlgorithm implements Behavior<OptionToAlgorithmParameters, OptionToAlgorithmResult> {

	@Override
	public boolean accept(OptionToAlgorithmParameters parameters) {
		final String option = parameters.getOption();
		return "-bm".equals(option);
	}

	@Override
	public OptionToAlgorithmResult execute(OptionToAlgorithmParameters parameters) {
		return new OptionToAlgorithmResult(new BoyerMooreAlgorithm());
	}

}
