package pl.enigmatic.kck.components;

import java.util.Random;

public enum Directions {

	N, E, S, W, NE, SE, SW, NW;

	private static final Directions[] values = Directions.class
			.getEnumConstants();
	private static final Random random = new Random(System.currentTimeMillis());

	/**
	 * @param id
	 * @return Direction z danym id, lub null je¿li id jest poza przedzia³em <0,
	 *         7>
	 */
	public static Directions get(int id) {

		if (id >= 0 && id < values.length) {
			return values[id];
		}
		System.out.println("Id w parametrze : " + id);
		System.err
				.println("Metoda Directions get(int id) zwróci³a wartoœæ null ");
		return null;
	}

	/**
	 * 
	 * @return randomowy kierunek prostej.
	 */
	public static Directions getRadndom() {
		int id = (random.nextInt(values.length + 1) % values.length);
		return get(id);
	}

	/**
	 * 
	 * @param direction
	 *            kierunek po którym nie chcemy mieæ naprzeciwleg³ego kierunku.
	 *            Np. Kierunku S w parametrze metoda wylosuje dowolny kierunek
	 *            ró¿ny od N itd...
	 * @return Randomowy Direction NIEPRZECIWLEG£Y do direction podanego w
	 *         parametrze
	 */
	public static Directions getRandom(Directions direction) {
		Directions result;
		do {
			result = getRadndom();
		} while (result == direction.getOpposite());
		return result;
	}

	public Directions getOpposite() {
		switch (this) {
		case N:
			return S;
		case S:
			return N;
		case W:
			return E;
		case E:
			return W;
		case NW:
			return SE;
		case SE:
			return NW;
		case NE:
			return SW;
		case SW:
			return NE;
		default:
			return null;
		}
	}

	/**
	 * Sprawdza czy kierunek jest przeciwleg³y do tego podanego w parametrze
	 * 
	 * @param directions
	 * @return true je¿li jest przeciwleg³y, false w innym wypadku.
	 */
	public boolean isOpposite(Directions direction) {
		return true;
	}
}
