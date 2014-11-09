package pl.enigmatic.kck.components;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.text.StyledEditorKit.BoldAction;

public abstract class PathComponent {

	protected Point start;
	protected Point end;
	protected Directions direction;

	public void drawAsFirst(Graphics g) {
		drawInner(g);
	}

	public abstract void drawInner(Graphics g);

	public void drawAsLast(Graphics g) {
		drawInner(g);
	}

	public abstract void drawAsLast(Graphics g, int x);

	public Point getStart() {
		return start;
	}

	public void setStart(Point a) {
		start = a;
	}

	public Point getEnd() {
		return end;
	}

	public void setEnd(Point b) {
		end = b;
	}

	public Directions getDirection() {
		return direction;
	}

	public void setDirection(Directions direction) {
		this.direction = direction;
	}

	/**
	 * Sprawdza czy punkt p należy do II ćwiartku płaszczyzny kartezjańskiej
	 * (wyświetlanej w oknie)
	 * 
	 * @param p
	 * @return true jeśli należy
	 */
	public boolean plainContains(Point p) {
		return ((p != null) && (p.x > 0) && (p.y > 0));
	}

	/**
	 * Sprawdza czy komponent zaczyna się lub kończy w punkcie p
	 * 
	 * @param p
	 * @return zwraca true jeśli punkt p jest początkiem lub końcem tego
	 *         komponentu w przeciwnym razie false
	 */
	public boolean contains(Point p) {
		return (p.equals(start) || p.equals(end));
	}

	public int getLeftBound() {
		return start.x < end.x ? start.x : end.x;
	}

	public int getRightBound() {
		return start.x > end.x ? start.x : end.x;
	}

	public int getUpBound() {
		return start.y < end.y ? start.y : end.y;
	}

	public int getBottomBound() {
		return start.y > end.y ? start.y : end.y;
	}

	public abstract void drawInner(Graphics g, int nr);

}
