package events;

import javax.swing.JLabel;

public class FenetreIncrementer {
	
	private JLabel label;
	private ReponseAuClic reponseAuClic;
	
	public FenetreIncrementer(JLabel label, ReponseAuClic reponseAuClic) {
		this.label = label;
		this.reponseAuClic = reponseAuClic;
	}
	
	public JLabel getLabel() {
		return label;
	}
	
	public ReponseAuClic getReponseAuClic() {
		return reponseAuClic;
	}

}
