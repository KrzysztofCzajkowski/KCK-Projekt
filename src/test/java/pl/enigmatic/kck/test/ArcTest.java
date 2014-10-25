package pl.enigmatic.kck.test;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ArcTest extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
	
		JFrame f = new JFrame("");
		ArcTest arc = new ArcTest();
		f.add(arc, BorderLayout.CENTER);
		f.setSize(500, 500);
		f.setVisible(true);
	}
	
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setStroke(new BasicStroke(7));
		
		g2.setPaint(Color.BLUE);
		
		
		Point A = new Point(100, 100);
		int height = 50;
		int width = 100;
		
		
		g2.drawArc(A.x, A.y, width, height, 0, 180);

		g2.fillOval(A.x - 5, A.y + (height / 2), 10, 10);
		//g2.fillOval(A.x + width, A.y, 10, 10);
	}
	
}
