package pl.enigmatic.kck;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import pl.enigmatic.kck.components.Landmark;
import pl.enigmatic.kck.components.PathComponent;
import pl.enigmatic.kck.map.Map;
import pl.enigmatic.kck.map.MapFactory;
import pl.enigmatic.kck.map.QuantifiedMap;
import pl.enigmatic.kck.path.Path;
import pl.enigmatic.kck.path.PathFinder;
import pl.enigmatic.kck.ui.Painter;

public class MainApp extends JFrame implements ActionListener, Runnable, PathFinder.Stopper {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Painter painter = new Painter();
	private final JButton button = new JButton("Losuj");
	
	private final JTextField lengthTextField = new JTextField("10");
	private final JTextField landmarksNumberTextField = new JTextField("15");
	private final JLabel landmarksLabel = new JLabel("Landmarks: ");
	private final JLabel lengthLabel = new JLabel("Path length: ");
	
	private Thread thread;
	private final PathFinder pathFinder = new PathFinder();
	private boolean stop = false;
	
	public MainApp() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JScrollPane scroll = new JScrollPane(painter);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		add(scroll, BorderLayout.CENTER);
		
		JPanel buttonPanel = new JPanel(new FlowLayout()); // Ustawia jeden za drugim
		buttonPanel.add(landmarksLabel);
		buttonPanel.add(landmarksNumberTextField);
		buttonPanel.add(lengthLabel);
		buttonPanel.add(lengthTextField);
		buttonPanel.add(button);
		add(buttonPanel, BorderLayout.SOUTH);

		button.addActionListener(this);
		actionPerformed(null);
		
		
	}

	public static void main(String[] args) {
		MainApp f = new MainApp();
		f.setSize(600, 500);
		f.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (thread == null) {
			button.setText("Zatrzymaj"); // od³¹cza przycisk losuj
			thread = new Thread(this);
			stop = false;
			thread.start();
		} else { // wchodzi tu jak kliknê zatrzymaj
			//thread.stop();
			stop = true;
			thread = null;
			button.setText("Losuj");
		}

		// start wykonuje run na nowym w¹tku
	}

	public void run() {
		int length = Integer.parseInt(lengthTextField.getText());
		int landmarksNumber = Integer.parseInt(landmarksNumberTextField.getText());
		Path p = pathFinder.randomize(length);
		
		MapFactory.setBottomBound(p.getBottomBound());
		MapFactory.setRightBound(p.getRightBound());
		
		// zmiana rozmiaru mapy skwantowanej
		MapFactory.getQMap().resizeQM(QuantifiedMap.mapToQMCoordinate(MapFactory.getRightBound()) + 1,
				QuantifiedMap.mapToQMCoordinate(MapFactory.getBottomBound()) + 1);
		
		// dodawanie elementów œcie¿ki do mapy skwantowanej
		for(PathComponent pc : pathFinder.getPath().getSegments()) {
			MapFactory.getQMap().addMapItem(pc.getStart().x, pc.getStart().y);
			MapFactory.getQMap().addMapItem(pc.getEnd().x, pc.getEnd().y);
			//MapFactory.getQMap().addMapItem(abs(pc.getStart().x - pc.getEnd().x),
			//		abs(pc.getStart().y - pc.getEnd().y));
			//TODO Dodawanie œrodka œcie¿ki do mapy skwantowanej.
			
		}
		
		// wstawianie landmarków
		Map map = MapFactory.createRandomMap(landmarksNumber);
		
		
		stop = false;
		
		painter.setPath(p);
		painter.setMap(map);
		painter.setSize(p.getRightBound() + 100, p.getBottomBound() + 100);
		painter.setMinimumSize(painter.getSize());
		painter.setPreferredSize(painter.getSize());
		painter.repaint();
		button.setText("Losuj");
		thread = null;
		
		

	}

	public boolean stop() {
		return stop;
	}

}
