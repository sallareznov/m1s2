package parsing.options.algorithm;

import algorithms.Algorithm;
import algorithms.ShiftOrAlgorithm;

public class ShiftOrOptionToAlgorithm implements OptionToAlgorithm {

	@Override
	public boolean accept(String option) {
		return "-so".equals(option);
	}

	@Override
	public Algorithm getAlgorithm() {
		return new ShiftOrAlgorithm();
	}

}
