package events;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FermetureFenetre extends WindowAdapter {
	
	@Override
	public void windowClosing(WindowEvent e) {
		System.out.println("--> Fenêtre en cours de fermeture");
		System.exit(0);
	}

}
