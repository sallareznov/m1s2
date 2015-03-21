package mst.sort;

import java.util.Comparator;

import mst.bean.Edge;

public class EdgesComparator implements Comparator<Edge> {

	@Override
	public int compare(Edge arg0, Edge arg1) {
		return arg0.getWeight() - arg1.getWeight();
	}

}
