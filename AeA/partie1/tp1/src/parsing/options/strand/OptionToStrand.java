package parsing.options.strand;

import bases.util.NonExistentPairingException;
import pattern.Strand;

public interface OptionToStrand {
	
	boolean accept(String option);
	
	Strand getStrand(Strand mainStrand) throws NonExistentPairingException;

}
