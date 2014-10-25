package pl.enigmatic.kck.map;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import pl.enigmatic.kck.components.Landmark;

public class MapFactory {
	private static final Random random = new Random(System.currentTimeMillis());

	private static int rightBound = 300; // przerobić później na normalny
	private static int bottomBound = 300;
	
	private static String landmarksNames[] = { "czarnyDom.jpg",
			"czerwonyDom.jpg", "zieloneDrzewo.jpg", "czarneDrzewo.jpg" };

	public static Map createRandomMap(int landmarkNumber) {
		Map m = new Map();
		m.setLandmarks(getRamdomLandmarks(landmarkNumber));
		return m;
	}
	
	public static Landmark getRandomLandmark() {
		Landmark l = new Landmark(
				landmarksNames[random.nextInt(landmarksNames.length)
						% landmarksNames.length]);
		l.setCoordinate(randomCoordinate());
	
		return l;
	}

	public static ArrayList<Landmark> getRamdomLandmarks(int number) {
		ArrayList<Landmark> list = new ArrayList<Landmark>();
		for (int i = 0; i < number; i++) {
			list.add(getRandomLandmark());
		}
		return list;
	}

	public static int getRightBound() {
		return rightBound;
	}

	public static void setRightBound(int right) {
		rightBound = right;
	}

	public static int getBottomBound() {
		return bottomBound;
	}

	public static void setBottomBound(int bottom) {
		bottomBound = bottom;
	}

	private static Point randomCoordinate() {
		Point p = new Point();
		p.x = random.nextInt(rightBound) % rightBound;
		p.y = random.nextInt(bottomBound) % bottomBound;
		return p;
	}
}
