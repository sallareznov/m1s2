package mst;

import java.util.Arrays;

public class VertexMarkingManager {
	
	private boolean[] markings;
	
	public VertexMarkingManager(int n) {
		markings = new boolean[n];
	}
	
	public void mark(int i) {
		markings[i] = true;
	}
	
	public boolean isMarked(int i) {
		return markings[i];
	}
	
	public void reset() {
		Arrays.fill(markings, false);
	}

}
