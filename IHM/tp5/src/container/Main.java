package container;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextArea;

import layout.VBoxLayout;

public class Main {

	public static void frameWithBorderLayout() {
		final JFrame frameBorderLayout = new JFrame("JFrame");
		final BorderLayout borderLayout = new BorderLayout(3, 5);
		frameBorderLayout.getContentPane().setLayout(borderLayout);
		frameBorderLayout.setVisible(true);
		frameBorderLayout.setSize(400, 300);
		frameBorderLayout.setLocation(0, 0);
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
	}

	public static void frameWithFlowLayout() {
		final JFrame frameFlowLayout = new JFrame("JFrame");
		frameFlowLayout.getContentPane().setLayout(
				new FlowLayout(FlowLayout.CENTER));
		for (int i = 1; i <= 16; i++) {
			final JButton button = new JButton("Bouton " + i);
			frameFlowLayout.add(button);
		}
		frameFlowLayout.setVisible(true);
		frameFlowLayout.setSize(400, 300);
		frameFlowLayout.setLocation(0, 0);
	}

	public static void frameWithGridLayout() {
		final JFrame frameGridLayout = new JFrame("JFrame");
		final GridLayout gridLayout = new GridLayout(4, 4);
		gridLayout.setHgap(3);
		gridLayout.setVgap(3);
		frameGridLayout.getContentPane().setLayout(gridLayout);
		for (int i = 1; i <= 16; i++) {
			final JButton button = new JButton("Bouton " + i);
			frameGridLayout.add(button);
		}
		frameGridLayout.setSize(400, 300);
		frameGridLayout.setVisible(true);
		frameGridLayout.setLocation(0, 0);
	}

	public static void frameWithBoxLayout() {
		final JFrame frameBoxLayout = new JFrame("JFrame");
		final BoxLayout boxLayout = new BoxLayout(
				frameBoxLayout.getContentPane(), BoxLayout.Y_AXIS);
		frameBoxLayout.getContentPane().setLayout(boxLayout);
		frameBoxLayout.add(new JButton("Bouton 1"));
		frameBoxLayout.add(new JButton("Bouton 2"));
		frameBoxLayout.add(new Box.Filler(new Dimension(100, 100),
				new Dimension(100, 100), new Dimension(100, 100)));
		frameBoxLayout.add(new JButton("Bouton 3"));
		frameBoxLayout.setSize(400, 300);
		frameBoxLayout.setVisible(true);
		frameBoxLayout.setLocation(0, 0);
	}

	public static void frameWithGridBagLayout() {
		final JFrame frameGridBagLayout = new JFrame("JFrame");
		final GridBagLayout gridBagLayout = new GridBagLayout();
		frameGridBagLayout.setSize(400, 300);
		frameGridBagLayout.setVisible(true);
		frameGridBagLayout.setLocation(0, 0);
		frameGridBagLayout.getContentPane().setLayout(gridBagLayout);
		final GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridx = 0;
		constraints.gridy = 0;
		int i = 1;
		while (i <= 4) {
			frameGridBagLayout.add(new JButton("Bouton " + i++), constraints);
			constraints.gridx++;
		}
		constraints.gridy++;
		constraints.gridx = 0;
		constraints.gridwidth = 4;
		frameGridBagLayout.add(new JButton("Bouton " + i++), constraints);
		constraints.gridy++;
		constraints.gridwidth = 3;
		frameGridBagLayout.add(new JButton("Bouton " + i++), constraints);
		constraints.gridx = 3;
		constraints.gridwidth = 1;
		frameGridBagLayout.add(new JButton("Bouton " + i++), constraints);
		constraints.gridy++;
		constraints.gridx = 0;
		constraints.gridheight = 2;
		constraints.ipady = 25;
		frameGridBagLayout.add(new JButton("Bouton " + i++), constraints);
		constraints.gridx++;
		constraints.gridwidth = 3;
		constraints.gridheight = 1;
		constraints.ipady = 0;
		frameGridBagLayout.add(new JButton("Bouton " + i++), constraints);
		constraints.gridy++;
		frameGridBagLayout.add(new JButton("Bouton " + i++), constraints);
	}

	public static void frameWithNoLayout() {
		final JFrame frameWithNoLayout = new JFrame("JFrame");
		frameWithNoLayout.setLayout(null);

		final Insets insets = frameWithNoLayout.getInsets();
		int x = 25;
		int y = 5;
		for (int i = 1; i <= 9; i++) {
			final JButton button = new JButton("Bouton " + i);
			Dimension size = button.getPreferredSize();
			button.setBounds(x + insets.left, y + insets.top, size.width,
					size.height);
			frameWithNoLayout.add(button);
			x += 25;
			y += 35;
		}
		frameWithNoLayout.setSize(400, 400);
		frameWithNoLayout.setLocation(0, 0);
		frameWithNoLayout.setVisible(true);
	}

	public static void frameWithBorderLayoutAndJPanel() {
		final JFrame frameBorderLayout = new JFrame("JFrame");
		final BorderLayout borderLayout = new BorderLayout(3, 5);
		frameBorderLayout.getContentPane().setLayout(borderLayout);
		frameBorderLayout.setVisible(true);
		frameBorderLayout.setSize(400, 300);
		frameBorderLayout.setLocation(0, 0);
		frameBorderLayout.setResizable(false);
		final JPanel panelNorth = new JPanel();
		for (int i = 1; i <= 3; i++) {
			panelNorth.add(new JButton("Bouton " + i));
		}
		final JButton buttonWest = new JButton("Ouest");
		final JButton buttonCenter = new JButton("Centre");
		final JButton buttonEast = new JButton("Est");
		final JButton buttonSouth = new JButton("Sud");
		frameBorderLayout.add(panelNorth, BorderLayout.PAGE_START);
		frameBorderLayout.add(buttonWest, BorderLayout.LINE_START);
		frameBorderLayout.add(buttonCenter, BorderLayout.CENTER);
		frameBorderLayout.add(buttonEast, BorderLayout.LINE_END);
		frameBorderLayout.add(buttonSouth, BorderLayout.PAGE_END);
	}

	public static void demoVBox() {
		JFrame fenetre = new JFrame("JFrame");
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetre.setLocation(100, 100);
		fenetre.setLayout(new VBoxLayout());
		fenetre.add(new JButton("Bouton 1"));
		fenetre.add(new JButton("Tres long bouton 2"));
		fenetre.add(new JButton("Super super long bouton 3"));
		fenetre.add(new JButton("Court bouton 4"));
		fenetre.setVisible(true);
		fenetre.pack();
	}
	
	public static void frameWithCustomMenu() {
		final JFrame frame = new JFrame("Editeur de texte");
		final JMenuBar menuBar = new JMenuBar();
		final JMenu fileMenu = new JMenu("Fichier");
		final JMenu editMenu = new JMenu("Edition");
		final JMenu formatMenu = new JMenu("Format");
		menuBar.add(fileMenu);
		menuBar.add(editMenu);
		menuBar.add(formatMenu);
		fileMenu.add(new JMenuItem("Nouveau"));
		fileMenu.add(new JMenuItem("Ouvrir"));
		fileMenu.add(new JMenuItem("Enregistrer"));
		fileMenu.add(new JMenuItem("Enregistrer sous"));
		fileMenu.add(new JSeparator());
		fileMenu.add(new JMenuItem("Mise en page"));
		fileMenu.add(new JMenuItem("Imprimer"));
		fileMenu.add(new JSeparator());
		fileMenu.add(new JMenuItem("Quitter"));
		frame.add(menuBar, BorderLayout.PAGE_START);
		final JTextArea textArea = new JTextArea();
		final Font italicAtt = new Font("Arial", Font.ITALIC, 12);
		textArea.setFont(italicAtt);
		textArea.setText("TP5 IHM. Université Lille 1");
		frame.add(textArea, BorderLayout.CENTER);
		final JPanel panel = new JPanel();
		frame.add(panel, BorderLayout.PAGE_END);
		final JLabel label = new JLabel("Rechercher :");
		final JLabel arrowLeft = new JLabel(new ImageIcon("previous_motif.gif"));
		final JLabel arrowRight = new JLabel(new ImageIcon("next_motif.gif"));
		panel.add(label);
		panel.add(arrowLeft);
		panel.add(arrowRight);
		/*panel.add(new JButton("<-"));
		panel.add(new JButton("->"));*/
		panel.add(new JButton("Tout surligner"));
		
		frame.setSize(400, 300);
		frame.setLocation(0, 0);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		// frameWithBorderLayout();
		// frameWithFlowLayout();
		// frameWithGridLayout();
		// frameWithGridBagLayout();
		// frameWithNoLayout();
		// frameWithBorderLayoutAndJPanel();
		frameWithCustomMenu();
	}

}
