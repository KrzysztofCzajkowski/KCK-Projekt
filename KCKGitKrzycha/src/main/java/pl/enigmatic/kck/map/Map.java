package pl.enigmatic.kck.map;

import java.util.ArrayList;
import java.util.Iterator;

import pl.enigmatic.kck.components.Landmark;

public class Map implements Iterable<Landmark> {
	
	private ArrayList<Landmark> landmarks = new ArrayList<Landmark>();

	public ArrayList<Landmark> getLandmarks() {
		return landmarks;
	}

	public void setLandmarks(ArrayList<Landmark> landmarks) {
		this.landmarks = landmarks;
	}

	public Iterator<Landmark> iterator() {
		return landmarks.iterator();
	}

}
