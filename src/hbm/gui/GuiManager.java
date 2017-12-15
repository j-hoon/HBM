package hbm.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

public class GuiManager extends JFrame {
	
	// 
	private static final long serialVersionUID = 1L;

	// Swing Components
//	private JFrame guiFrame;
	private JPanel panel;
	private JTextArea textArea;
	private TitledBorder panelTitledBorder = new TitledBorder("");
	
	// Constructor
	public GuiManager() {
		super();
		System.out.println("GuiManager constructed.");
		
		// frame settings
		setTitle("Hoon's Book Manager");
		setLayout(null);
		setSize(800, 600);
		
		// panel settings
		this.panel = new JPanel();
//		this.panel.setBounds(10, 10, 770, 480);
		this.panel.setLocation(10, 10);
		this.panel.setSize(getWidth()-35, getHeight()-60);
		this.panel.setBorder(panelTitledBorder);
		this.panel.setVisible(true);
		
		// textArea settings
		this.textArea = new JTextArea("Text Area");
		this.textArea.setVisible(true);
		
		// add components and final settings
		this.panel.add(this.textArea);
		getContentPane().add(this.panel);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
}
