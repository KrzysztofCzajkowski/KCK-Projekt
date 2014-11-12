package pl.enigmatic.kck.components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.QuadCurve2D;

public class Curve extends PathComponent {

	protected Point controlPoint;
	private static final int smallShift = 50;
	private static final int bigShift = 100;

	public Curve() {
		controlPoint = new Point(0, 0);
	}

	public Point getControlPoint() {
		return controlPoint;
	}

	public void setControlPoint(Point controlPoint) {
		this.controlPoint = controlPoint;
	}

	public Directions getDirection() {
		return direction;
	}

	public void setDirection(Directions direction) {
		this.direction = direction;
	}

	@Override
	public void drawInner(Graphics g) {
		g.setColor(Color.BLACK);

		Graphics2D g2 = (Graphics2D) g;
		g2.draw(new QuadCurve2D.Float(start.x, start.y, controlPoint.x,
				controlPoint.y, end.x, end.y));
		g.fillOval(end.x - 10, end.y - 10, 20, 20);
	}

	@Override
	public void drawInner(Graphics g, int nr) {
		drawInner(g);
		g.drawString(String.valueOf(nr), start.x + 18, start.y + 18);
	}

	public void setSmallCurve(PathComponent pathComponent, CurveDirections d){

		setCurve(pathComponent, d, smallShift);
	}
	
	public void setBigCurve(PathComponent pathComponent, CurveDirections d) {
		setCurve(pathComponent, d, bigShift);
	}
	/**
	 * Tworzy łagodny łuk na podstawie odcinka l dobierając odpowiednio punkty
	 * kontrolne (TODO) (chwilowo robi tylko dla podstawowych 4 kierunków
	 * świwata..
	 * 
	 * @param pathComponent
	 *            - prosta, z której tworzymy łuk
	 * @return łuk, stworzony na podstawie prostej line i kierunku direction
	 */
	private void setCurve(PathComponent pathComponent, CurveDirections d, int shift) {
		this.start = pathComponent.getStart();
		this.end = pathComponent.getEnd();
		this.direction = pathComponent.getDirection();

		Point center = new Point(Math.abs(this.start.x + this.end.x) / 2,
				Math.abs(this.start.y + this.end.y) / 2);
		Point control = new Point(center);
		switch (d) {
			case LEFT: {
				if (direction == Directions.N) {
					control = new Point(this.start.x - shift, center.y);
				} else if (direction == Directions.S) {
					control = new Point(this.start.x + shift, center.y);
				} else if (direction == Directions.E) {
					control = new Point(center.x, center.y - shift);
				} else if (direction == Directions.W) {
					control = new Point(center.x, center.y + shift);
				}
				break;
			}
			case RIGHT: {
				if (direction == Directions.N) {
					control = new Point(this.start.x + shift, center.y);
				} else if (direction == Directions.S) {
					control = new Point(this.start.x - shift, center.y);
				} else if (direction == Directions.E) {
					control = new Point(center.x, center.y + shift);
				} else if (direction == Directions.W) {
					control = new Point(center.x, center.y - shift);
				}
				break;
			}
		}

		this.controlPoint = control;

	}
	
	public void drawAsFirst(Graphics g) {
		g.setColor(Color.BLUE);
		Graphics2D g2 = (Graphics2D) g;
		g2.draw(new QuadCurve2D.Float(start.x, start.y, controlPoint.x,
				controlPoint.y, end.x, end.y));
		g.fillOval(start.x - 10 , start.y - 10, 20, 20);
		g.fillOval(end.x - 10 , end.y - 10, 20, 20);
		g.drawString("START", start.x, start.y - 20);
	}

	public void drawAsLast(Graphics g) {
		g.setColor(Color.GREEN);
		Graphics2D g2 = (Graphics2D) g;
		g2.draw(new QuadCurve2D.Float(start.x, start.y, controlPoint.x,
				controlPoint.y, end.x, end.y));
		g.fillOval(start.x - 10 , start.y - 10, 20, 20);
		g.fillOval(end.x - 10 , end.y - 10, 20, 20);
		g.drawString("END", end.x, end.y + 20);
	}

	@Override
	public void drawAsLast(Graphics g, int x) {
		drawAsLast(g);
		g.drawString(String.valueOf(x), start.x, start.y + 20);
	
		
	}
	
	public String toString() {
		return "Luk " + super.toString() + " Control Point: (" + controlPoint.x + "," + controlPoint.y + ")"; 
	}

}
