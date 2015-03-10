package events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;

public class ReponseAuClic implements ActionListener {
	
	private JLabel label;
	
	public ReponseAuClic(JLabel label) {
		this.label = label;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		final Integer currentNumber = Integer.parseInt(label.getText());
		label.setText((currentNumber + 1) + "");
		System.out.println("--> Clic sur le bouton");
	}

}
