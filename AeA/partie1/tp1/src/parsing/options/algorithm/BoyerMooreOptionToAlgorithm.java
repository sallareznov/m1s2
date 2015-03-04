package parsing.options.algorithm;

import algorithms.Algorithm;
import algorithms.BoyerMooreAlgorithm;

public class BoyerMooreOptionToAlgorithm implements OptionToAlgorithm {

	@Override
	public boolean accept(String option) {
		return "-bm".equals(option);
	}

	@Override
	public Algorithm getAlgorithm() {
		return new BoyerMooreAlgorithm();
	}

}
