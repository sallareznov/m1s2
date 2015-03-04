package main.util;

import patterns.Strand;
import algorithms.util.StrandOccurences;

public class StrandSearchingResult {
	
	private Strand strand;
	private StrandOccurences occurences;
	
	public StrandSearchingResult(Strand strand, StrandOccurences strandOccurences) {
		this.strand = strand;
		this.occurences = strandOccurences;
	}
	
	public Strand getStrand() {
		return strand;
	}
	
	public StrandOccurences getOccurences() {
		return occurences;
	}
	
	@Override
	public String toString() {
		return (strand + " : " + occurences);
	}

}
