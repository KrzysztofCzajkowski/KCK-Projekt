package pl.enigmatic.kck.test;

import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MoveableSquare implements MouseMotionListener, MouseListener,
		ComponentListener {

	private static boolean isInRange(int x, int left, int right) {
		return (x >= left && x <= right);
	}

	private static int clamp(int x, int left, int right) {
		if (x < left) {
			return left;
		}
		if (x > right) {
			return right;
		}
		return x;
	}

	private Component owner;
	private int x = 0;
	private int y = 0;
	private int size = 50;
	private boolean dragged = false;
	private int x0 = 0;
	private int y0 = 0;

	public void connect(Component owner) {
		this.owner = owner;
		owner.addMouseListener(this);
		owner.addMouseMotionListener(this);
		owner.addComponentListener(this);
	}

	public void disconnect() {
		owner.removeMouseListener(this);
		owner.removeMouseMotionListener(this);
		owner.removeComponentListener(this);
		owner = null;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public void draw(Graphics2D g) {
		g.fillRect(x, y, size, size);
	}

	public void mouseClicked(MouseEvent e) {

	}

	public void mousePressed(MouseEvent e) {
		if (dragged = isInRange(e.getX(), x, x + size)
				&& isInRange(e.getY(), y, y + size)) {
			x0 = e.getX() - x;
			y0 = e.getY() - y;
		}
	}

	public void mouseReleased(MouseEvent e) {
		dragged = false;
	}

	public void mouseEntered(MouseEvent e) {

	}

	public void mouseExited(MouseEvent e) {

	}

	public void mouseDragged(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();

		if (dragged) {
			this.x = clamp(x - x0, 0, owner.getWidth() - size);
			this.y = clamp(y - y0, 0, owner.getHeight() - size);
		}
	}

	public void mouseMoved(MouseEvent e) {

	}

	public void componentResized(ComponentEvent e) {
		x = clamp(x, 0, owner.getWidth() - size);
		y = clamp(y, 0, owner.getHeight() - size);

	}

	public void componentMoved(ComponentEvent e) {

	}

	public void componentShown(ComponentEvent e) {

	}

	public void componentHidden(ComponentEvent e) {

	}

}
