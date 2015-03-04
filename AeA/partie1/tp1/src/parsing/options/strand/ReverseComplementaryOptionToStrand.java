package parsing.options.strand;

import patterns.Strand;
import bases.util.NonExistentPairingException;

public class ReverseComplementaryOptionToStrand implements OptionToStrand {

	@Override
	public boolean accept(String option) {
		return "-revComp".equals(option);
	}

	@Override
	public Strand getStrand(Strand mainStrand) throws NonExistentPairingException {
		return mainStrand.getReverseComplementary();
	}

}
