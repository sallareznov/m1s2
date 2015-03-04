package parsing.options.strand;

import bases.util.NonExistentPairingException;
import patterns.Strand;

public class ReverseOptionToStrand implements OptionToStrand {

	@Override
	public boolean accept(String option) {
		return "-rev".equals(option);
	}

	@Override
	public Strand getStrand(Strand mainStrand) throws NonExistentPairingException {
		return mainStrand.getReverse();
	}

}
