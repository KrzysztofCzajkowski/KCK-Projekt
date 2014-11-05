package pl.enigmatic.kck.map;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import pl.enigmatic.kck.components.Landmark;

public class MapFactory {
	private static final Random random = new Random(System.currentTimeMillis());

	private static int x = 3;
	private static int y = 3;
	private static int rightBound = x * 50; // przerobić później na normalny
	private static int bottomBound = y * 50;

	private static QuantifiedMap QMap = new QuantifiedMap(x, y); //skwantowana mapa kwadratów o boku 50 pikseli
	
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
		
		for (int i=0; i < QMap.getX(); i++) {
			for (int j=0; j<QMap.getY(); j++) {
				
				if (QMap.checkMapCoordinate(QuantifiedMap.mapToQMCoordinate(i), QuantifiedMap.mapToQMCoordinate(j)))
					System.out.print("true ");
				else
					System.out.print("false ");
			}
			System.out.println();
		}
		
		p.x = (random.nextInt(rightBound) % rightBound);
		p.y = (random.nextInt(bottomBound) % bottomBound);
		
		
		System.out.println(QuantifiedMap.coordinateQuantified(p.x));
		System.out.println(QuantifiedMap.coordinateQuantified(p.y));
		System.out.println(QMap.getX());
		System.out.println(QMap.getY());
		
		
		
		//dopóki wylosowane punkty są już zajęte, losuj nowe
		while (QMap.checkMapCoordinate(QuantifiedMap.coordinateQuantified(p.x), QuantifiedMap.coordinateQuantified(p.y)) != false) {
			p.x = (random.nextInt(rightBound) % rightBound);
			p.y = (random.nextInt(bottomBound) % bottomBound);
		}
		if (QuantifiedMap.coordinateQuantified(p.x) > QMap.getX() || QuantifiedMap.coordinateQuantified(p.y) > QMap.getY())
			System.out.print("punkt poza zakresem");
		
		p.x = QuantifiedMap.coordinateQuantified(p.x);
		p.y = QuantifiedMap.coordinateQuantified(p.y);
		QMap.addMapItem(p.x, p.y);

		return p;
	}
	
	public static QuantifiedMap getQMap() {
		return QMap;
	}
}
