package parsing.options.strand;

import manager.Behavior;
import pattern.Strand;
import bases.util.NonExistentPairingException;

public class ReverseComplementaryOptionToStrand implements Behavior<OptionToStrandParameters, OptionToStrandResult> {

	@Override
	public boolean accept(OptionToStrandParameters parameters) {
		final String option = parameters.getOption();
		return "-revComp".equals(option);
	}

	@Override
	public OptionToStrandResult execute(OptionToStrandParameters parameters) {
		try {
			final Strand strand = parameters.getStrand();
			return new OptionToStrandResult(strand.getReverseComplementary());
		} catch (NonExistentPairingException e) {
			return null;
		}
	}

}
