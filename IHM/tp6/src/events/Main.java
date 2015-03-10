package events;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Main {
	
	private JFrame frame;
	private JLabel number;
	private JButton incrementButton;
	
	public Main(int width, int height) {
		frame = new JFrame("Incrémenter");
		frame.getContentPane().setLayout(new GridLayout(2, 1));
		number = new JLabel("0");
		//number.setAlignmentX(Component.CENTER_ALIGNMENT);
		incrementButton = new JButton("Incrémenter");
		incrementButton.addActionListener(new ReponseAuClic(number));
		frame.add(number);
		frame.add(incrementButton);
		frame.setSize(width, height);
		frame.setVisible(true);
		frame.addWindowListener(new FermetureFenetre());
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Main(200, 100);
	}

}
