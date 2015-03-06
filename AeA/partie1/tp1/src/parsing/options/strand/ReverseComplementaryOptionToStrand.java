package parsing.options.strand;

import manager.Behavior;
import pattern.Strand;

public class ReverseComplementaryOptionToStrand implements
		Behavior<OptionToStrandParameters, OptionToStrandResult> {

	@Override
	public boolean accept(OptionToStrandParameters parameters) {
		final String option = parameters.getOption();
		return "-revComp".equals(option);
	}

	@Override
	public OptionToStrandResult execute(OptionToStrandParameters parameters) {
		final Strand strand = parameters.getStrand();
		return new OptionToStrandResult(strand.getReverseComplementary());
	}

}
