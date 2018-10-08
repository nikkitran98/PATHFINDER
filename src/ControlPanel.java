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
		setBackground(Color.blue);
		
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
		top2.setLayout(new GridLayout(1,5));
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
		top2.add(helpButton);
		top2.add(aboutButton);
		
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
		
		activityField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO
				// variable = activityField.getText();
			}
		});
		
		durationField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO
				// variable = durationField.getText();
			}
		});
		
		dependancyField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO
				// variable = dependancyField.getText();
			}
		});
		
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
				String message = "Activity Name: Enter the name of activity in corresponding text field.\n" + 
						"Activity Duration: Enter amount of time it takes to complete the activity in the corresponding text field.\n" + 
						"Activity Dependencies: Enter the activities that must be completed before you can start the activity being entered. If there are none, enter “none”. \n" + 
						"Add: Use the data fields name, duration, and dependency to add an entry to the path.\n" + 
						"Delete: Deletes the entry that matches the current information in the activity name data field.\n" + 
						"Calculate: Calculates the possibly paths and the time to complete the entire path. \n" + 
						"Restart: Discards all of the previous paths, allowing you to start a new network diagram.\n" + 
						"?: Help section; you are currently here\n" + 
						"A: Defines the purpose of the program";
				JOptionPane.showMessageDialog(null, message);
			}
			else if (event.getSource() == aboutButton) {
				// TODO
				String message = "The purpose of PATHFINDER is to improve project management \n"
						+ "planning and increase efficiency and accuracy in project planning \n"
						+ "delivery times. Users are able to log activities and expected \n"
						+ "completion times to find the most efficient path to project completion. \n"
						+ "The system itself will take the user inputs, create a \n"
						+ "network diagram to determine all the paths in the network. \n";
				JOptionPane.showMessageDialog(null, message);
			}
			
		}
		
	}
}
