package parsing.options.algorithm;

import manager.Behavior;
import algorithms.KarpRabinAlgorithm;

public class KarpRabinOptionToAlgorithm implements
		Behavior<OptionToAlgorithmParameters, OptionToAlgorithmResult> {

	@Override
	public boolean accept(OptionToAlgorithmParameters parameters) {
		final String option = parameters.getOption();
		return "-kr".equals(option);
	}

	@Override
	public OptionToAlgorithmResult execute(
			OptionToAlgorithmParameters parameters) {
		return new OptionToAlgorithmResult(new KarpRabinAlgorithm());
	}

}
