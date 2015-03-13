package mst;

import java.util.LinkedList;
import java.util.List;

public class MST {
	
	private List<Ridge> ridges;
	
	public MST() {
		ridges = new LinkedList<Ridge>();
	}
	
	public void addArete(Ridge arete) {
		ridges.add(arete);
	}
	
	public int getTotalWeight() {
		int totalWeight = 0;
		for (final Ridge ridge : ridges) {
			totalWeight += ridge.getWeight();
		}
		return totalWeight;
	}

}
