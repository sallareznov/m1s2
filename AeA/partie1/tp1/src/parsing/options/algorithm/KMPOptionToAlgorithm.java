package parsing.options.algorithm;

import algorithms.Algorithm;
import algorithms.KMPAlgorithm;

public class KMPOptionToAlgorithm implements OptionToAlgorithm {

	@Override
	public boolean accept(String option) {
		return "-kmp".equals(option);
	}

	@Override
	public Algorithm getAlgorithm() {
		return new KMPAlgorithm();
	}

}
