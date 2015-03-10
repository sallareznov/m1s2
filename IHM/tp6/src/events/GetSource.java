package events;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class GetSource {
	
	public static void main(String[] args) {
		final JFrame frame = new JFrame("getSource() test");
		frame.setSize(300, 150);
		final JButton button1 = new JButton("Bouton 1");
		final JButton button2 = new JButton("Bouton 2");
		final JButton button3 = new JButton("Bouton 3");
		final JLabel label = new JLabel();
		frame.setLayout(new GridLayout(4, 1, 2, 2));
		final List<JButton> buttons = Arrays.asList(button1, button2, button3);
		frame.add(label);
		for (final JButton button : buttons) {
			frame.add(button);
			button.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					final JButton source = (JButton) (arg0.getSource());
					label.setText(source.getText());
				}
			});
		}
		frame.setVisible(true);
		frame.addWindowListener(new FermetureFenetre());
	}

}
