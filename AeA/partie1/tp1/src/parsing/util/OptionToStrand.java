package parsing.util;

import bases.util.NonExistentPairingException;
import patterns.Strand;

public interface OptionToStrand {
	
	boolean accept(String option);
	
	Strand getStrand(Strand mainStrand) throws NonExistentPairingException;

}
