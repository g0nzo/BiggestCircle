import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Panel;
import java.awt.Shape;
import java.awt.event.KeyListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class View extends JFrame {

	private DrawPane drawPane;
	private final int POINT_SIZE;
	private ArrayList<Point2D> goodPointsList = new ArrayList<Point2D>();
	private ArrayList<Point2D> badPointsList = new ArrayList<Point2D>();
	private Ellipse2D.Double circle = new Ellipse2D.Double();
	
	
	public View(int width, int height, int pointSize) {
		setTitle("ga");
		setSize(width, height);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

		drawPane = new DrawPane();
		setContentPane(drawPane);
		
		this.POINT_SIZE = pointSize;
	}

	class DrawPane extends JPanel {
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D) g;

			for (Point2D point : goodPointsList) {
				Ellipse2D.Double circle = new Ellipse2D.Double(point.getX(), point.getY(), 3, 3);
				g2.setColor(Color.blue);
				g2.fill(circle);
				g2.draw(circle);
			}
			
			for (Point2D point : badPointsList) {
				Ellipse2D.Double circle = new Ellipse2D.Double(point.getX(), point.getY(), 3, 3);
				g2.setColor(Color.red);
				g2.fill(circle);
				g2.draw(circle);
			}
			g2.setColor(Color.black);
			g2.draw(circle);
		}
	}

	public void setGoodPoints(ArrayList<Point2D> goodPointsList) {
		drawPane.removeAll();
		this.goodPointsList = goodPointsList;
		drawPane.repaint();
	}
	
	public void setBadPoints(ArrayList<Point2D> badPointsList) {
		drawPane.removeAll();
		this.badPointsList = badPointsList;
		drawPane.repaint();
	}
	
	public void setCircle(Ellipse2D.Double circle) {
		drawPane.removeAll();
		this.circle = circle;
		drawPane.repaint();
	}
	
	public void setResetListener(KeyListener listener) {
		addKeyListener(listener);
	}
}
