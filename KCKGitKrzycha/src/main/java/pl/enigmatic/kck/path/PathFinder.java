package pl.enigmatic.kck.path;

import pl.enigmatic.kck.components.Directions;
import pl.enigmatic.kck.components.Line;

public class PathFinder {
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
						&& !l.setLine(Path.STARTPOINT, Directions.getRadndom()));
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
							&& !l2.setLine(path.getSegment(i - 1).getEnd(),
									Directions.getRandom(previousDirection)));

				} while (!stopper.stop() && path.containsPoint(l2.getEnd()));
				if (!path.containsPoint(l2.getEnd())){
					path.add(l2);
				}
			}
		}

		return path;
	}

	/**
	 * Tworzy randomową ścieżkę o długości podanej w parametrze
	 * @param length - długość ścieżki
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
