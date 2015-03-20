package mst;

import java.util.Comparator;

public class RidgeComparator implements Comparator<Ridge> {

	@Override
	public int compare(Ridge arg0, Ridge arg1) {
		return arg0.getWeight() - arg1.getWeight();
	}

}
