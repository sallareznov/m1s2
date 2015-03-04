package main.util;

import java.util.LinkedList;
import java.util.List;

public class StrandSearchingResultsManager {
	
	private List<StrandSearchingResult> results;
	
	public StrandSearchingResultsManager() {
		results = new LinkedList<StrandSearchingResult>();
	}
	
	public void addResult(StrandSearchingResult result) {
		results.add(result);
	}
	
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		for (StrandSearchingResult result : results) {
			builder.append(result);
			builder.append("\n");
		}
		return builder.toString();
	}

}
