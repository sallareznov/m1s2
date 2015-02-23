package containers;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		// BorderLayout
		final JFrame frameBorderLayout = new JFrame("JFrame");
		final BorderLayout borderLayout = new BorderLayout(3, 3);
		frameBorderLayout.setLayout(borderLayout);
		frameBorderLayout.setVisible(true);
		frameBorderLayout.setSize(400, 300);
		frameBorderLayout.setLocation(0, 200);
		frameBorderLayout.setResizable(false);
		final JButton buttonNorth = new JButton("Nord");
		final JButton buttonWest = new JButton("Ouest");
		final JButton buttonCenter = new JButton("Centre");
		final JButton buttonEast = new JButton("Est");
		final JButton buttonSouth = new JButton("Sud");
		frameBorderLayout.add(buttonNorth, BorderLayout.PAGE_START);
		frameBorderLayout.add(buttonWest, BorderLayout.LINE_START);
		frameBorderLayout.add(buttonCenter, BorderLayout.CENTER);
		frameBorderLayout.add(buttonEast, BorderLayout.LINE_END);
		frameBorderLayout.add(buttonSouth, BorderLayout.PAGE_END);
		// FlowLayout
		final JFrame frameFlowLayout = new JFrame("JFrame");
		frameFlowLayout.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER));
		for (int i = 1 ; i <= 16 ; i++) {
			final JButton button = new JButton("Bouton " + i);
			frameFlowLayout.add(button);
		}
		frameFlowLayout.setVisible(true);
		frameFlowLayout.setSize(400, 300);
		frameFlowLayout.setLocation(500, 200);
		// GridBagLayout
		final JFrame frameGridLayout = new JFrame("JFrame");
		final GridLayout gridLayout = new GridLayout(4, 4);
		gridLayout.setHgap(3);
		gridLayout.setVgap(3);
		frameGridLayout.getContentPane().setLayout(gridLayout);
		for (int i = 1 ; i <= 16 ; i++) {
			final JButton button = new JButton("Bouton " + i);
			frameGridLayout.add(button);
		}
		frameGridLayout.setSize(400, 300);
		frameGridLayout.setVisible(true);
		frameGridLayout.setLocation(1000, 200);
		// BoxLayout
		final JFrame frameBoxLayout = new JFrame("JFrame");
		final BoxLayout boxLayout = new BoxLayout(frameBoxLayout.getContentPane(), BoxLayout.Y_AXIS);
		frameBoxLayout.setLayout(boxLayout);
		frameBoxLayout.add(new JButton("Bouton 1"));
		frameBoxLayout.add(new JButton("Bouton 2"));
		frameBoxLayout.add(new Box.Filler(new Dimension(100, 100), new Dimension(100, 100), new Dimension(100, 100)));
		frameBoxLayout.add(new JButton("Bouton 3"));
		frameBoxLayout.setSize(400, 300);
		frameBoxLayout.setVisible(true);
		frameBoxLayout.setLocation(0, 0);
	}

}
