package pl.enigmatic.kck.ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;

import javax.swing.JPanel;

import pl.enigmatic.kck.components.Landmark;
import pl.enigmatic.kck.components.PathComponent;
import pl.enigmatic.kck.map.Map;
import pl.enigmatic.kck.path.Path;

public class Painter extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7773724965238957291L;
	private static final int fontSize = 20;
	// private Graphics g;
	private Path path;
	private Map map;

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	public Path getPath() {
		return path;
	}

	public void setPath(Path path) {
		this.path = path;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		this.setBackground(Color.WHITE);
		g.setFont(new Font("TimesRoman", Font.PLAIN, fontSize));

		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setStroke(new BasicStroke(7));

		if (map != null) {
			for (Landmark l : map) {
				l.drawLandMark(g);
			}

		}

		if (path != null) {
			ArrayList<PathComponent> segments = path.getSegments();

			int size = segments.size();

			if (size > 0) {
				segments.get(0).drawAsFirst(g);
			}
			for (int i = 1; i < size - 1; i++) {
				segments.get(i).drawInner(g, i + 1);
			}
			if (size > 1) {
				segments.get(size - 1).drawAsLast(g);
			}
		}
	}
}
