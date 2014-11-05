package pl.enigmatic.kck.map;

/**Klasa obsługująca mapę złożoną z kwadratów 50x50 pikseli.
 * 
 * @author Krzysztof Czajkowski
 *
 */
public class QuantifiedMap {
	private static int x = 3;
	private static int y = 3;
	private static Boolean[][] map;
	
	/**Konstruktor przyjmujący szerokośc i wysokośc (liczbę wierszy i kolumn).
	 */
	public QuantifiedMap(int a, int b) {
		map = new Boolean[a][b];
		x=a;
		y=b;
	}
	
	/**Dodaje element o podanych współrzędnych do mapy skwantowanej.
	 * 
	 * @return Null
	 */
	public void addMapItem(int x, int y) {
		int i = mapToQMCoordinate(x);
		int j = mapToQMCoordinate(y);
		map[i][j] = true;
	}
	
	/**Usuwa element z kwadratu o podanych współrzędnych.
	 * 
	 * @return Null
	 */
	public void removeMapItem(int x, int y) {
		int i = mapToQMCoordinate(x);
		int j = mapToQMCoordinate(y);
		map[i][j] = false;
	}
	
	/**Sprawdza czy kwadrat zawierający punkt o podanych współrzędnych jest zajęty.
	 * 
	 * @return Boolean
	 */
	public Boolean checkMapCoordinate(int x, int y) {
		int i = mapToQMCoordinate(x);
		int j = mapToQMCoordinate(y);
		return map[i][j];
	}
	
	/**Zmienia rozmiar mapy, tracąc dotychczasowe informacje/
	 * 
	 * @return Null
	 */
	public void resizeQM(int new_x, int new_y) {
		int i = mapToQMCoordinate(new_x);
		int j = mapToQMCoordinate(new_y);
		x = new_x;
		y = new_y;
		map = new Boolean [i][j];
	}
	
	/**Zwraca szerokośc tablicy.
	 * 
	 * @return int
	 */
	public int getX() {
		return x;
	}
	
	/**Zwraca wysokośc tablicy.
	 * 
	 * @return int
	 */
	public int getY() {
		return y;
	}
	
	/**Zwraca środek przedziału odpowiedniego kwadratu o boku 50 pikseli, w którym znajdje się argument x.
	 * 
	 * @return int
	 */
	public static int coordinateQuantified(int x) {
		int i = 50;
		while (x > i)
			i += 50;
		return i - 25;
	}
	
	/**Zwraca dla danej współrzędnej odpowiadający indeks w mapie skwantowanej.
	 * 
	 * @return int
	 */
	public static int mapToQMCoordinate(int x) {
		int i = 0;
		while ((i + 1) * 50 < x)
			i++;
		return i;
	}
}
