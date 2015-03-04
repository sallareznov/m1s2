package parsing.options.algorithm;

import java.util.LinkedList;
import java.util.List;

import algorithms.Algorithm;
import algorithms.KMPAlgorithm;

public class OptionsToAlgorithmsManager {
	
	private List<OptionToAlgorithm> optionsToAlgorithms;
	
	public OptionsToAlgorithmsManager() {
		optionsToAlgorithms = new LinkedList<OptionToAlgorithm>();
	}
	
	public void addOptionToAlgorithm(OptionToAlgorithm optionToAlgorithm) {
		optionsToAlgorithms.add(optionToAlgorithm);
	}
	
	public Algorithm getAdequateAlgorithm(String option) {
		for (final OptionToAlgorithm optionToAlgorithm : optionsToAlgorithms) {
			if (optionToAlgorithm.accept(option))
				return optionToAlgorithm.getAlgorithm(); 
		}
		return new KMPAlgorithm();
	}

}
