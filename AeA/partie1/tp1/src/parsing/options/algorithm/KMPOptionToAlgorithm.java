package parsing.options.algorithm;

import manager.Behavior;
import algorithms.KMPAlgorithm;

public class KMPOptionToAlgorithm implements Behavior<OptionToAlgorithmParameters, OptionToAlgorithmResult> {

	@Override
	public boolean accept(OptionToAlgorithmParameters parameters) {
		final String option = parameters.getOption();
		return "-kmp".equals(option);
	}

	@Override
	public OptionToAlgorithmResult execute(
			OptionToAlgorithmParameters parameters) {
		return new OptionToAlgorithmResult(new KMPAlgorithm());
	}

}
