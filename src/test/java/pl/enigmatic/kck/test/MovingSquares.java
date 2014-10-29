package pl.enigmatic.kck.test;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Arc2D;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MovingSquares extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	final static BasicStroke wideStroke = new BasicStroke(8.0f);

	public static void main(String[] args) {
		JFrame f = new JFrame("");
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		MovingSquares panel = new MovingSquares();
		f.setContentPane(panel);
		positionFrame(f);
		f.setVisible(true);
	}

	private final Timer timer = new Timer();
	private final TimerTask task = new TimerTask() {

		@Override
		public void run() {
			x += vx;
			y += vy;
			if (x < 0 || x > getWidth()) {
				vx *= -1;
			}
			if (y < 0 || y > getHeight()) {
				vy *= -1;
			}
			repaint();
		}
	};

	private final MoveableSquare square = new MoveableSquare();

	public MovingSquares() {
		timer.scheduleAtFixedRate(task, 0L, 50);
		square.connect(this);

	}

	private int x = 0;
	private int y = 0;
	private int vx = 7;
	private int vy = 10;

	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g.clearRect(0, 0, getWidth(), getHeight());
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		g2.setPaint(Color.BLUE);
		g2.fillRect(x, y, 100, 100);

	}

	public static void positionFrame(Component c) {
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		c.setSize((int) (dimension.getWidth() / 2),
				(int) (dimension.getHeight() / 2));
		int x = (int) ((dimension.getWidth() - c.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - c.getHeight()) / 2);
		c.setLocation(x, y);
	}
}
