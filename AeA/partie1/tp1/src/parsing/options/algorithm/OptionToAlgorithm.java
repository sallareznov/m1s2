package parsing.options.algorithm;

import algorithms.Algorithm;

public interface OptionToAlgorithm {
	
	boolean accept(String option);
	
	Algorithm getAlgorithm();

}
