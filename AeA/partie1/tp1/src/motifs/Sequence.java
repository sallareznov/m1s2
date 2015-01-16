package motifs;

import java.util.ArrayList;
import java.util.List;

public class Sequence {
	
	private List<Strand> strands;
	
	public Sequence() {
		strands = new ArrayList<Strand>();
	}
	
	public List<Strand> getStrands() {
		return strands;
	}

}
