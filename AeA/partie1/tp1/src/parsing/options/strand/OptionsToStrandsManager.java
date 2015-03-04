package parsing.options.strand;

import java.util.LinkedList;
import java.util.List;

import bases.util.NonExistentPairingException;
import patterns.Strand;

public class OptionsToStrandsManager {
	
	private List<OptionToStrand> optionsToStrands;
	
	public OptionsToStrandsManager() {
		optionsToStrands = new LinkedList<OptionToStrand>();
	}
	
	public void addOptionToStrand(OptionToStrand optionToStrand) {
		optionsToStrands.add(optionToStrand);
	}
	
	public Strand getAdequateStrand(String option, Strand mainStrand) throws NonExistentPairingException {
		for (final OptionToStrand optionToStrand : optionsToStrands) {
			if (optionToStrand.accept(option))
				return optionToStrand.getStrand(mainStrand);
		}
		return mainStrand;
	}

}
