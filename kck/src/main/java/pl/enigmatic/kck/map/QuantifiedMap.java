package pl.enigmatic.kck.map;

/**Klasa obs�uguj�ca map� z�o�on� z kwadrat�w 50x50 pikseli.
 * 
 * @author Krzysztof Czajkowski
 *
 */
public class QuantifiedMap {
	private static int x = 3;
	private static int y = 3;
	private static Boolean[][] map;
	
	/**Konstruktor przyjmuj�cy szerok�� i wysoko�� (liczb� wierszy i kolumn).
	 */
	public QuantifiedMap(int a, int b) {
		map = new Boolean[a][b];
		x=a;
		y=b;
		
		for (int i=0; i < x; i++) {
			for (int j=0; j < y; j++)
				map[i][j] = false;
		}
	}
	
	/**Dodaje element o podanych wsp�rz�dnych do mapy skwantowanej.
	 * 
	 * @return Null
	 */
	public void addMapItem(int x, int y) {
		int i = mapToQMCoordinate(x);
		int j = mapToQMCoordinate(y);
		map[i][j] = true;
	}
	
	/**Usuwa element z kwadratu o podanych wsp�rz�dnych.
	 * 
	 * @return Null
	 */
	public void removeMapItem(int x, int y) {
		int i = mapToQMCoordinate(x);
		int j = mapToQMCoordinate(y);
		map[i][j] = false;
	}
	
	/**Sprawdza czy kwadrat zawieraj�cy punkt o podanych wsp�rz�dnych jest zaj�ty.
	 * 
	 * @return Boolean
	 */
	public Boolean checkMapCoordinate(int x, int y) {
		int i = mapToQMCoordinate(x);
		int j = mapToQMCoordinate(y);
		return map[i][j];
	}
	
	/**Zmienia rozmiar mapy, trac�c dotychczasowe informacje/
	 * 
	 * @return Null
	 */
	public void resizeQM(int new_x, int new_y) {
		x = new_x;
		y = new_y;
		map = new Boolean [new_x][new_y];
		
		for (int i=0; i < new_x; i++) {
			for (int j=0; j < new_y; j++)
				map[i][j] = false;
		}
	}
	
	/**Zwraca szeroko�� tablicy.
	 * 
	 * @return int
	 */
	public int getX() {
		return x;
	}
	
	/**Zwraca wysoko�� tablicy.
	 * 
	 * @return int
	 */
	public int getY() {
		return y;
	}
	
	/**Zwraca �rodek przedzia�u odpowiedniego kwadratu o boku 50 pikseli,
	 * w kt�rym znajdje si� argument x.
	 * 
	 * @return int
	 */
	public static int coordinateQuantified(int x) {
		int i = 50;
		while (x > i)
			i += 50;
		return i - 25;
	}
	
	/**Zwraca dla danej wsp�rz�dnej odpowiadaj�cy indeks w mapie skwantowanej.
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
