package events;

import java.util.LinkedList;
import java.util.List;

public class Curve {
	
	private List<Point> points;

	public Curve() {
		points = new LinkedList<Point>();
	}
	
	public List<Point> getPoints() {
		return points;
	}

	public void addPoint(Point P) {
		points.add(P);
	}

	public void clear() {
		points.clear();
	}

}
