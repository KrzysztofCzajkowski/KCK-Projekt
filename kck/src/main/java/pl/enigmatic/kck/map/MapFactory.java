package pl.enigmatic.kck.map;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import pl.enigmatic.kck.components.Landmark;

public class MapFactory {
	private static final Random random = new Random(System.currentTimeMillis());

	private static int x = 3;
	private static int y = 3;
	private static int rightBound = x * 50; // przerobi� p�niej na normalny
	private static int bottomBound = y * 50;

	private static QuantifiedMap QMap = new QuantifiedMap(x, y); //skwantowana mapa kwadrat�w o boku 50 pikseli
	
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

		p.x = (random.nextInt(rightBound) % rightBound);
		p.y = (random.nextInt(bottomBound) % bottomBound);
		
		//dop�ki wylosowane punkty s� ju� zaj�te, losuj nowe
		while (QuantifiedMap.mapToQMCoordinate(p.x) > QMap.getX()-1 || QuantifiedMap.mapToQMCoordinate(p.y) > QMap.getY()-1 ||
				QMap.checkMapCoordinate(QuantifiedMap.coordinateQuantified(p.x), QuantifiedMap.coordinateQuantified(p.y))) {
			p.x = (random.nextInt(rightBound) % rightBound);
			p.y = (random.nextInt(bottomBound) % bottomBound);
		}

		p.x = QuantifiedMap.coordinateQuantified(p.x);
		p.y = QuantifiedMap.coordinateQuantified(p.y);
		QMap.addMapItem(p.x, p.y);

		return p;
	}
	
	public static QuantifiedMap getQMap() {
		return QMap;
	}
}
