package parsing.options.strand;

import pattern.Strand;

public class OptionToStrandParameters {
	
	private String option;
	private Strand strand;
	
	public OptionToStrandParameters(String option, Strand strand) {
		this.option = option;
		this.strand = strand;
	}
	
	public String getOption() {
		return option;
	}
	
	public Strand getStrand() {
		return strand;
	}
	
}
