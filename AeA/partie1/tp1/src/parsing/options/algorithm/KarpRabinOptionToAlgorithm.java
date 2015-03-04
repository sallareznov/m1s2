package parsing.options.algorithm;

import algorithms.Algorithm;
import algorithms.KarpRabinAlgorithm;

public class KarpRabinOptionToAlgorithm implements OptionToAlgorithm {

	@Override
	public boolean accept(String option) {
		return "-kr".equals(option);
	}

	@Override
	public Algorithm getAlgorithm() {
		return new KarpRabinAlgorithm();
	}

}
