package pl.enigmatic.kck.path;

import pl.enigmatic.kck.components.Directions;
import pl.enigmatic.kck.components.Line;
import pl.enigmatic.kck.components.PathComponent;

import java.awt.Point;
import java.util.ArrayList;

public class PathFinder {
	// stopper - interfejs
	private Path path = new Path();
	public Path randomize(int length, Stopper stopper) {

		for (int i = 0; i < length && !stopper.stop(); i++) {
			if (i == 0) {
				Line l = new Line();
				do {
					System.out
							.println("Szukam pierwszego odcinka w randomowej œcie¿ce");
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
								+ " - tego odcinka w randomowej œcie¿ce");
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

	public Path getPath() {
		return path;
	}

	/**
	 * Tworzy randomow¹ œcie¿kê o d³ugoœci podanej w parametrze
	 * @param length - d³ugoœæ œcie¿ki
	 * @return Randomowa œcie¿ka d³ugoœci length
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
