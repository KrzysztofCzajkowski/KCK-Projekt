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
							.println("Szukam pierwszego odcinka w randomowej �cie�ce");
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
								+ " - tego odcinka w randomowej �cie�ce");
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
	 * Tworzy randomow� �cie�k� o d�ugo�ci podanej w parametrze
	 * @param length - d�ugo�� �cie�ki
	 * @return Randomowa �cie�ka d�ugo�ci length
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
