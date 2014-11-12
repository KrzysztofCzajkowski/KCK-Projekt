package pl.enigmatic.kck.components;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.text.StyledEditorKit.BoldAction;

public abstract class PathComponent {

	protected Point start;
	protected Point end;
	protected String type;

	public void drawAsFirst(Graphics g) {
		drawInner(g);
	}

	public abstract void drawInner(Graphics g);

	public void drawAsLast(Graphics g) {
		drawInner(g);
	}

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

	/**
	 * Sprawdza czy punkt p nale�y do II �wiartki p�aszczyzny kartezja�skiej
	 * (wy�wietlanej w oknie)
	 * 
	 * @param p
	 * @return true je�li nale�y
	 */
	public boolean plainContains(Point p) {
		return ((p != null) && (p.x > 0) && (p.y > 0));
	}

	/**
	 * Sprawdza czy komponent zaczyna si� lub ko�czy w punkcie p
	 * 
	 * @param p
	 * @return zwraca true je�li punkt p jest pocz�tkiem lub ko�cem tego
	 *         komponentu w przeciwnym razie false
	 */
	public boolean contains(Point p) {
		return (p.equals(start) || p.equals(end));
	}

	public int getRightBound() {
		return start.x > end.x ? start.x : end.x;
	}

	public int getBottomBound() {
		return start.y > end.y ? start.y : end.y;
	}

	public abstract  void drawInner(Graphics g, int nr);

}
