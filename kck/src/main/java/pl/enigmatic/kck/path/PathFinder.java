package pl.enigmatic.kck.path;

import java.util.Random;

import pl.enigmatic.kck.components.Curve;
import pl.enigmatic.kck.components.CurveDirections;
import pl.enigmatic.kck.components.Directions;
import pl.enigmatic.kck.components.Line;

public class PathFinder {
	private static final Random random = new Random(System.currentTimeMillis());

	// stopper - interfejs
	public Path randomize(int length, Stopper stopper) {
		Path path = new Path();

		for (int i = 0; i < length && !stopper.stop(); i++) {
			if (i == 0) {
				Line l = new Line();
				do {
					System.out
							.println("Szukam pierwszego odcinka w randomowej ścieżce");
				} while (!stopper.stop()
						&& !l.setEndPoint(Path.STARTPOINT,
								Directions.getRadndom()));
				path.add(l);
			} else {
				Line l2 = new Line();

				Directions previousDirection = ((Line) (path.getSegment(i - 1)))
						.getDirection();
				do {
					do {
						System.out.println("Szukam " + (i + 1)
								+ " - tego odcinka w randomowej ścieżce");
					} while (!stopper.stop()
							&& !l2.setEndPoint(path.getSegment(i - 1).getEnd(),
									Directions.getRandom(previousDirection)));

				} while (!stopper.stop() && path.containsPoint(l2.getEnd()));
				if (!path.containsPoint(l2.getEnd())) {
					path.add(l2);
				}
			}
		}
		// Zamiana losowej ilości odcinków na łuki
		int numberOfCurves = random.nextInt(path.size()) % path.size();
		System.out.println("Liczba łuków: " + numberOfCurves);
		int i = 0;
		while (i < numberOfCurves) {
			// indeks do zmiany krzywej
			int index = random.nextInt(path.size()) % path.size();
			// losuje czy będzie duzy luk czy maly
			int curveType = random.nextInt(path.size()) % path.size();
			if (path.get(index) instanceof Line) {
				Curve c = new Curve();
				if (curveType % 2 == 0) {
					c.setBigCurve(path.get(index),
							CurveDirections.getRandomCurveDirection());
				} else {
					c.setSmallCurve(path.get(index), CurveDirections.getRandomCurveDirection());
				}
				path.set(index, c);
				i++;
				System.out.println("dodano łuk nr: " + i + "jako odcinek numer:" + index);
			}
		}
		return path;
	}

	/**
	 * Tworzy randomową ścieżkę o długości podanej w parametrze
	 * 
	 * @param length
	 *            - długość ścieżki
	 * @return Randomową ścieżkę długości length
	 */
	public Path randomize(int length) {
		return randomize(length, nonStopper);
	}

	public interface Stopper {
		boolean stop();
	}

	private static final Stopper nonStopper = new Stopper() {

		public boolean stop() {
			return false;
		}
	};
}
