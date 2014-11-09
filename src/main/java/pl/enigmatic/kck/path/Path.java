package pl.enigmatic.kck.path;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;

import pl.enigmatic.kck.components.PathComponent;

public class Path implements Iterable<PathComponent> {

	public static final Point STARTPOINT = new Point(400, 400);

	private ArrayList<PathComponent> segments = new ArrayList<PathComponent>();

	public ArrayList<PathComponent> getSegments() {
		return segments;
	}

	public void setSegments(ArrayList<PathComponent> path) {
		this.segments = path;
	}

	public PathComponent getSegment(int index) {
		return segments.get(index);
	}

	public static Point getStartpoint() {
		return STARTPOINT;
	}

	public boolean add(PathComponent comp) {
		return segments.add(comp);
	}

	/**
	 * sprawdza czy œciezka ma jakiœ komponent zaczynaj¹cy siê lub koñcz¹cy w
	 * punkcie p
	 * 
	 * @param point
	 * @return
	 */
	public boolean containsPoint(Point point) {
		for (int i = 0; i < segments.size(); i++) {
			if (segments.get(i).contains(point)) {
				return true;
			}
		}
		return false;
	}

	public int getRightBound() {
		int max = 0;
		for (PathComponent pc : segments) {
			if (max < pc.getRightBound()) {
				max = pc.getRightBound();
			}
		}
		return max;
	}

	public int getBottomBound() {
		int max = 0;
		for (PathComponent pc : segments) {
			if (max < pc.getBottomBound()) {
				max = pc.getBottomBound();
			}
		}
		return max;
	}

	public Iterator<PathComponent> iterator() {
		return segments.iterator();
	}
}
