package pl.enigmatic.kck.components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Line extends PathComponent {

	private static final int length = 100;

	@Override
	public void drawInner(Graphics g) {
		g.setColor(Color.BLACK);
		g.drawLine(start.x, start.y, end.x, end.y);
		//g.fillOval(start.x - 10 , start.y - 10, 20, 20);
		g.fillOval(end.x - 10 , end.y - 10, 20, 20);
	}	
	
	@Override
	public void drawInner(Graphics g, int nr) {
		drawInner(g);
		//int middleX = (start.x + end.x) / 2;
		//int middleY = (start.y + end.y) / 2;
		g.drawString(String.valueOf(nr), start.x + 18, start.y + 18 );
	}
	
	public void drawAsFirst(Graphics g) {
		g.setColor(Color.BLUE);
		g.drawLine(start.x, start.y, end.x, end.y);
		g.fillOval(start.x - 10 , start.y - 10, 20, 20);
		g.fillOval(end.x - 10 , end.y - 10, 20, 20);
		g.drawString("START", start.x, start.y - 20);
	}

	public void drawAsLast(Graphics g) {
		g.setColor(Color.GREEN);
		g.drawLine(start.x, start.y, end.x, end.y);
		g.fillOval(start.x - 10 , start.y - 10, 20, 20);
		g.fillOval(end.x - 10 , end.y - 10, 20, 20);
		g.drawString("END", end.x, end.y + 20);
	}

	public void drawAsLast(Graphics g, int x) {
		drawAsLast(g);
		g.drawString(String.valueOf(x), start.x + 10, start.y + 20);
	}
	public Directions getDirection() {
		return direction;
	}

	public void setDirection(Directions direction) {
		this.direction = direction;
	}

	public int getLenght() {
		return length;
	}
	
/**
 * Ustawia linię z punktu A do Punktu B. Punkt B ustawia w zależności od kierunku.
 * Ustawioną linię dodaje do ścieżki (ArrayList path)
 * 
 */
	public boolean setEndPoint(Point A, Directions direction) {
		this.setStart(A);
		this.direction = direction;

		Point point;;
		switch (direction) {
			case S: {
				point = new Point(this.start.x, this.start.y + length);
				break;
			} case E: {
				point = new Point(this.start.x + length, this.start.y);
				break;
			} case SE : {
				point = new Point(this.start.x + length, this.start.y + length);
				break;
			} case N : {
					point = new Point(this.start.x, this.start.y - length);
					break;
			} case NE : {
					point = new Point(this.start.x + length, this.start.y - length);
					break;
			} case W : {
					point = new Point(this.start.x - length, this.start.y);
					break;
			} case SW : {
					point = new Point(this.start.x - length, this.start.y + length);
					break;
			} case NW : {
					point = new Point(this.start.x - length, this.start.y - length);
					break;
			} default : {
				point = null;
			}
			
		}
		
		if (plainContains(point)) {
			setEnd(point);
			System.out.println("Zatwierdzony odcinek : A = " + this.start + "\tB = " + point );
			return true;
		}
		System.out.println("NIE Zatwierdzony odcinek : A = " + this.start + "\tB = " + point );
		return false;
	}

}
