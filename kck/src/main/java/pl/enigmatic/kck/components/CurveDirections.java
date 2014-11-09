package pl.enigmatic.kck.components;

import java.util.Random;
/**
 * Zbiór Enum z kierunkiem Left / Right wykorzystywany do określenia w którą stronę skierowany jest łuk względem przechodzącego agenta.
 * @author Bartek
 *
 */
public enum CurveDirections {
	LEFT, RIGHT;
	
	private static Random random = new Random(System.currentTimeMillis());
	private static final CurveDirections[] values = CurveDirections.class
			.getEnumConstants();
	
	public static CurveDirections get(int id) {

		if (id >= 0 && id < values.length) {
			return values[id];
		}
		System.out.println("Id w parametrze : " + id);
		System.err
				.println("Metoda Directions get(int id) zwróciła wartość null ");
		return null;
	}
	
	
	static public CurveDirections getRandomCurveDirection() {
		int id = (random.nextInt(values.length + 1) % values.length);
		return get(id);}
}
