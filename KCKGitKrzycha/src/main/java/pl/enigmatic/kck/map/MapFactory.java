package pl.enigmatic.kck.map;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import pl.enigmatic.kck.components.Landmark;

public class MapFactory {
	private static final Random random = new Random(System.currentTimeMillis());

	private static int x = 3;
	private static int y = 3;
	private static int rightBound = x * 200; // przerobić później na normalny
	private static int bottomBound = y * 200;

	private static Boolean[][] QuantifiedMap = new Boolean[x][y]; //tablica kwadratów 200x200 pikseli
	
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
	
	public static Boolean[][] getQuantifiedMap() {
		return QuantifiedMap;
	}
	
	public static void setQuantifiedMapCoord(int x, int y, Boolean value) {
		int i = mapToQMCoordinate(x);
		int j = mapToQMCoordinate(y);
		QuantifiedMap[i][j] = value;
	}
	
	public static Boolean [][] resizeQM (Boolean [][] QuantifiedMap, int new_x, int new_y) {
		int i = mapToQMCoordinate(new_x);
		int j = mapToQMCoordinate(new_y);
		QuantifiedMap = new Boolean [i][j];
		return QuantifiedMap;
	}
	
	public static int coordinateQuantified(int x) {
		int i = 200;
		while (x > i)
			i += 200;
		return i - 100;
	}
	
	public static int mapToQMCoordinate(int x) {
		int i = 0;
		while ((i + 1) * 200 < x)
			i++;
		return i;
	}

	private static Point randomCoordinate() {
		Point p = new Point();
		p.x = (random.nextInt(rightBound) % rightBound);
		p.y = (random.nextInt(bottomBound) % bottomBound);
		
		//dopóki wylosowane punkty są już zajęte, losuj nowe
		while (QuantifiedMap[mapToQMCoordinate(p.x)][mapToQMCoordinate(p.y)]) {
			p.x = (random.nextInt(rightBound) % rightBound);
			p.y = (random.nextInt(bottomBound) % bottomBound);
		}
		
		p.x = coordinateQuantified(p.x);
		p.y = coordinateQuantified(p.y);
		setQuantifiedMapCoord(p.x, p.y, true);

		return p;
	}
}
