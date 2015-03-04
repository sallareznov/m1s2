package parsing.util;

import bases.util.NonExistentPairingException;
import patterns.Strand;

public class ComplementaryOptionToStrand implements OptionToStrand {

	@Override
	public boolean accept(String option) {
		return "-comp".equals(option);
	}

	@Override
	public Strand getStrand(Strand mainStrand) throws NonExistentPairingException {
		return mainStrand.getComplementary();
	}

}
