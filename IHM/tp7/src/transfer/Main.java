package transfer;

import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.TransferHandler;

public class Main extends JFrame {

	private static final long serialVersionUID = -8838570592226773755L;

	public void init() {
		setTitle("Drag police");
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		final JLabel label = new JLabel("IHM");
		final JFormattedTextField textField1 = new JFormattedTextField("Master 1 Info");
		final JFormattedTextField textField2 = new JFormattedTextField("Universite de Lille 1");
		textField1.addMouseListener(new DragMouseAdapter());
		textField2.addMouseListener(new DragMouseAdapter());
		textField1.setTransferHandler(new TransferHandler("font"));
		textField2.setTransferHandler(new TransferHandler("font"));
		label.setTransferHandler(new CustomTransferHandler());
		label.addMouseListener(new DragMouseAdapter());
		textField1.setFont(new Font("Arial", Font.BOLD, 12));
		textField1.setFont(new Font("Serif", Font.ITALIC, 12));
		add(label);
		add(textField1);
		add(textField2);
		setVisible(true);
		setSize(200, 100);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Main().init();
	}

}
