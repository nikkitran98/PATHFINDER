import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;

public class ControlPanel extends JPanel {
	
	private JPanel topPanel, bottomPanel;
	private JLabel activityLabel, durationLabel, dependancyLabel;
	private JTextField activityField, durationField, dependancyField;
	private JButton addButton, restartButton, calcButton, helpButton, aboutButton;
	private int width, height;
	
	
	public ControlPanel(int width, int height) {
		this.width = width;
		this.height = height;
		
		addButton = new JButton("Add");
		restartButton = new JButton("Restart");
		calcButton = new JButton("Calculate");
		helpButton = new JButton("?");
		aboutButton = new JButton("A");
	}
}
