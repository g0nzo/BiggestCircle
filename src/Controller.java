import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Point2D;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class Controller {
	private View view;
	private Algorithm algorithm;
	private boolean restart;

	public Controller(View view, Algorithm algorithm) {
		this.view = view;
		this.algorithm = algorithm;
		this.restart = false;
		
		view.setResetListener(new ResetListener());		
	}
	
	public void loop() {
		while(true) {
			if(restart) {
				algorithm.reset();
				restart = false;
			} else {
				algorithm.epoch();
				viewUpdate();
			}
		}
	}
	
	public void viewUpdate() {
		view.setGoodPoints(algorithm.getGoodPointsList());
		view.setBadPoints(algorithm.getBadPointsList());
		view.setCircle(algorithm.getCircle());
	}
	
	
	public class ResetListener implements KeyListener {
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_SPACE) {
				restart = true;
			}
		}

		public void keyReleased(KeyEvent arg0) {
			// TODO Auto-generated method stub

		}

		public void keyTyped(KeyEvent arg0) {
			// TODO Auto-generated method stub

		}

	}
}
