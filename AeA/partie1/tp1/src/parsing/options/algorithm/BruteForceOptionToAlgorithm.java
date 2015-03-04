package parsing.options.algorithm;

import algorithms.Algorithm;
import algorithms.BruteForceAlgorithm;

public class BruteForceOptionToAlgorithm implements OptionToAlgorithm {

	@Override
	public boolean accept(String option) {
		return "-bf".equals(option);
	}

	@Override
	public Algorithm getAlgorithm() {
		return new BruteForceAlgorithm();
	}

}
