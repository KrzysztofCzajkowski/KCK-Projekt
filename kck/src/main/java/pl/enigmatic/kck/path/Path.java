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
	 * sprawdza czy ściezka ma jakiś komponent zaczynający się lub kończący w
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

	public int getLeftBound() {
		int min = STARTPOINT.x;
		for (PathComponent pc : segments) {
			if (min > pc.getLeftBound()) {
				min = pc.getLeftBound();
			}
		}
		return min;
	}

	public int getUpBound() {
		int min = STARTPOINT.y;
		for (PathComponent pc : segments) {
			if (min > pc.getUpBound()) {
				min = pc.getUpBound();
			}
		}
		return min;
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

	public PathComponent set(int index, PathComponent element) {
		return segments.set(index, element);
	}
	

	public PathComponent get(int index) {
		return segments.get(index);
	}

	public int size() {
		return segments.size();
	}

	public Iterator<PathComponent> iterator() {
		return segments.iterator();
	}
	
	public String toString() {
		String result = "";
		int i = 0;
		for (PathComponent p : segments) {
			result += i + ". " +  p.toString();
			result += "\n";
			i++;
		}
		return result;
	}
}
