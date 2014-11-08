package pl.enigmatic.kck.components;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Landmark {


	private ImageIcon icon;
	private Image image;

	private String name;
	private Point coordinate;

	/**
	 * Tworzy Landmarka z losowymi wspó³rzêdnymi, które wybiera z przedzia³u (0,
	 * rightBound/bottomBound)
	 * 
	 * @param name
	 *            to nazwa landmarku czyli nazwa pliku jpg który reprezentuje
	 *            ten landmark w formie graficznej
	 * @param rightBound
	 * @param bottomBound
	 * @throws IOException
	 */
	public Landmark(String name) {
		super();
		this.name = name;
		coordinate = new Point(0, 0);

		try {
			icon = new ImageIcon(ImageIO.read(Landmark.class
					.getResourceAsStream("/images/" + name)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		image = icon.getImage();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Point getCoordinate() {
		return coordinate;
	}

	public void setCoordinate(Point coordinate) {
		this.coordinate = coordinate;
	}

	public void drawLandMark(Graphics g) {
		g.drawImage(this.image, coordinate.x, coordinate.y, null);
	}


}
