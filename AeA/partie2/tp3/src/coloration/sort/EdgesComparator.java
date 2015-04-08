package coloration.sort;

import java.util.Comparator;

import coloration.bean.Edge;

public class EdgesComparator implements Comparator<Edge> {

	@Override
	public int compare(Edge arg0, Edge arg1) {
		return arg0.getWeight() - arg1.getWeight();
	}

}
