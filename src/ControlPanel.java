import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;

public class ControlPanel extends JPanel {
	
	private JPanel topPanel, top1, top2, activityPanel, durationPanel, dependancyPanel, bottomPanel;
	private JLabel activityLabel, durationLabel, dependancyLabel;
	private JTextField activityField, durationField, dependancyField;
	private JButton addButton, restartButton, calcButton, helpButton, aboutButton;
	private int width, height;
	ButtonListener buttonlistener = new ButtonListener();
	
	public ControlPanel(int width, int height) {
		this.width = width;
		this.height = height;
		setLayout(new BorderLayout());
		
		topPanel = new JPanel();
		top1 = new JPanel();
		top2 = new JPanel();
		activityPanel = new JPanel();
		durationPanel = new JPanel();
		dependancyPanel = new JPanel();
		bottomPanel = new JPanel();
		
		activityLabel = new JLabel("Activity Name: ");
		durationLabel = new JLabel("Activity Duration: ");
		dependancyLabel = new JLabel("Activity Dependencies: ");
		
		activityField = new JTextField();
		durationField = new JTextField();
		dependancyField = new JTextField();
		
		addButton = new JButton("Add");
		restartButton = new JButton("Restart");
		calcButton = new JButton("Calculate");
		helpButton = new JButton("?");
		aboutButton = new JButton("A");
		
		topPanel.setLayout(new GridLayout(2,1));
		top1.setLayout(new GridLayout(3,1));
		top2.setLayout(new GridLayout(1,3));
		activityPanel.setLayout(new GridLayout(1,2));
		durationPanel.setLayout(new GridLayout(1,2));
		dependancyPanel.setLayout(new GridLayout(1,2));
		
		activityPanel.add(activityLabel);
		activityPanel.add(activityField);
		
		durationPanel.add(durationLabel);
		durationPanel.add(durationField);
		
		dependancyPanel.add(dependancyLabel);
		dependancyPanel.add(dependancyField);
		
		top1.add(activityPanel);
		top1.add(durationPanel);
		top1.add(dependancyPanel);
		
		top2.add(addButton);
		top2.add(calcButton);
		top2.add(restartButton);
		
		topPanel.add(top1);
		topPanel.add(top2);
		
		// TODO(Nikki): add about and help button + popup action message

		JSplitPane pane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, topPanel, bottomPanel);
		add(pane);
		setPreferredSize(new Dimension(width,height));
		
		addButton.addActionListener(buttonlistener);
		restartButton.addActionListener(buttonlistener);
		calcButton.addActionListener(buttonlistener);
		helpButton.addActionListener(buttonlistener);
		aboutButton.addActionListener(buttonlistener);
	}
	
	private JSplitPane JSPlitPane(int verticalSplit, JPanel topPanel2, JPanel bottomPanel2) {
		// TODO Auto-generated method stub
		return null;
	}

	private class ButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent event) {
			
			if(event.getSource() == addButton) {
				// TODO
			}
			else if (event.getSource() == restartButton) {
				// TODO
			}
			else if (event.getSource() == calcButton) {
				// TODO
			}
			else if (event.getSource() == helpButton) {
				// TODO
			}
			else if (event.getSource() == aboutButton) {
				// TODO
			}
			
		}
		
	}
}
