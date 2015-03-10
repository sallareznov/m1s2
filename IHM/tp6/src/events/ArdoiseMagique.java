package events;

/**
 * ArdoiseMagique.java
 *
 * @author <a href="mailto:gery.casiez@lifl.fr">Gery Casiez</a>
 * @version
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ArdoiseMagique extends JPanel {
	
	private static final long serialVersionUID = 8935553195107462209L;
	private LinkedList<Curve> curves;

	public ArdoiseMagique(){
		curves = new LinkedList<Curve>();
		curves.add(new Curve());
		setBackground(Color.WHITE);
	}

	public void addPoint(Integer x, Integer y) {
		final Point pointToAdd = new Point(x, y);
		curves.getLast().addPoint(pointToAdd);
		repaint();
	}

	public void newCurve() {
		curves.add(new Curve());
	}

	public void clear() {
		curves.clear();
		curves.add(new Curve());
		repaint();
	}
	
	public void removePoint(int x, int y) {
		Curve concernedCurve = null;
		Point concernedPoint = null;
		for (final Curve curve : curves) {
			for (final Point point : curve.getPoints()) {
				if (point.getX() == x && point.getY() == y) {
					concernedPoint = point;
					concernedCurve = curve;
				}
			}
		}
		if (concernedCurve != null && concernedPoint != null) {
			concernedCurve.getPoints().remove(concernedPoint);
			repaint();
		}
	}

	public void paintComponent(Graphics g) {
		Point previousPoint = null;
		Point currentPoint = null;
		super.paintComponent(g);

		final Iterator<Curve> itcurve = curves.iterator();

		previousPoint = new Point();

		// Pour chaque courbe
		while (itcurve.hasNext()) {
			final Iterator<Point> it = itcurve.next().getPoints().iterator();

			if (it.hasNext()) {
				previousPoint = it.next();
			}

			// Dessine les points d'une courbe
			while (it.hasNext()) {
				currentPoint = it.next();
				g.drawLine(previousPoint.getX(),previousPoint.getY(), currentPoint.getX(), currentPoint.getY());
				previousPoint = currentPoint;
			}
		}
	}
	
	public static void main(String[] args) {
		final JFrame frame = new JFrame("Ardoise magique");
		final ArdoiseMagique ardoiseMagique = new ArdoiseMagique();
		ardoiseMagique.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				ardoiseMagique.newCurve();
			}
			
		});
		ardoiseMagique.addMouseMotionListener(new MouseMotionAdapter() {
			
			@Override
			public void mouseDragged(MouseEvent arg0) {
				if (arg0.isMetaDown()) {
					ardoiseMagique.removePoint(arg0.getX(), arg0.getY());
					return;
				}
				ardoiseMagique.addPoint(arg0.getX(), arg0.getY());
			}
			
		});
		frame.setContentPane(ardoiseMagique);
		frame.setVisible(true);
		frame.setSize(500, 500);
		frame.addWindowListener(new FermetureFenetre());
	}
}