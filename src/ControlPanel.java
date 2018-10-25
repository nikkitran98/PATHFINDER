import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;

public class ControlPanel extends JPanel {
	
	private JPanel topPanel, top1, top2, activityPanel, durationPanel, dependancyPanel, bottomPanel, bottom1, bottom2;
	private JLabel activityLabel, durationLabel, dependancyLabel, outputPath, outputDependancy, outputDuration;
	private JTextField activityField, durationField, dependancyField;
	private JButton addButton, restartButton, processButton, helpButton, aboutButton;
	private int width, height;
	private String activity, dependancy;
	private int duration, count;
	private LinkedList list;
	private JTextArea output;
	
	ButtonListener buttonlistener = new ButtonListener();
	
	// constructor to configure panel
	public ControlPanel(LinkedList list, int width, int height) {
		this.width = width;
		this.height = height;
		this.list = list;
		setLayout(new BorderLayout());
		setBackground(Color.blue);
		setPreferredSize(new Dimension(width,height));
		
		topPanel = new JPanel();
		top1 = new JPanel();
		top2 = new JPanel();
		activityPanel = new JPanel();
		durationPanel = new JPanel();
		dependancyPanel = new JPanel();
		bottomPanel = new JPanel();
		bottom1 = new JPanel();
		bottom2 = new JPanel();
		
		activityLabel = new JLabel("Activity Name: ");
		durationLabel = new JLabel("Activity Duration: ");
		dependancyLabel = new JLabel("Activity Dependencies: ");
		outputPath = new JLabel("Paths");
		outputDependancy = new JLabel("Path Dependencies");
		outputDuration = new JLabel("Duration");
		
		output = new JTextArea (30,30);
		JScrollPane scrollPane = new JScrollPane(output);
		
		activityField = new JTextField();
		durationField = new JTextField();
		dependancyField = new JTextField();
		
		addButton = new JButton("Add");
		restartButton = new JButton("Restart");
		processButton = new JButton("Process");
		helpButton = new JButton("?");
		aboutButton = new JButton("A");
		
		activityPanel.setLayout(new GridLayout(1,2));
		activityPanel.add(activityLabel);
		activityPanel.add(activityField);
		
		durationPanel.setLayout(new GridLayout(1,2));
		durationPanel.add(durationLabel);
		durationPanel.add(durationField);
		
		dependancyPanel.setLayout(new GridLayout(1,2));
		dependancyPanel.add(dependancyLabel);
		dependancyPanel.add(dependancyField);
		
		top1.setLayout(new GridLayout(3,1));
		top1.add(activityPanel);
		top1.add(durationPanel);
		top1.add(dependancyPanel);
		
		top2.setLayout(new GridLayout(1,5));
		top2.add(addButton);
		top2.add(processButton);
		top2.add(restartButton);
		top2.add(helpButton);
		top2.add(aboutButton);
		
		topPanel.setLayout(new GridLayout(2,1));
		topPanel.add(top1);
		topPanel.add(top2);
		
		bottom1.setLayout(new GridLayout(1,3));
		bottom1.add(outputPath);
		bottom1.add(outputDependancy);
		bottom1.add(outputDuration);
		
		bottom2.add(scrollPane);
		
		bottomPanel.setLayout(new GridLayout(2,1));
		bottomPanel.add(bottom1);
		bottomPanel.add(bottom2);

		JSplitPane pane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, topPanel, bottomPanel);
		add(pane);
		
		addButton.addActionListener(buttonlistener);
		restartButton.addActionListener(buttonlistener);
		processButton.addActionListener(buttonlistener);
		helpButton.addActionListener(buttonlistener);
		aboutButton.addActionListener(buttonlistener);
		
		// TODO: make action listener to work
		activityField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				activity = activityField.getText();
			}
		});
		
		durationField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				duration = Integer.parseInt(durationField.getText());
			}
		});
		
		dependancyField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dependancy = dependancyField.getText();
			}
		});
		
	}

	private class ButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent event) {
			
			if(event.getSource() == addButton) {
		
				try {
					if(activityField.getText().isEmpty() || dependancyField.getText().isEmpty() || durationField.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Error: please make sure all fields are filled.");
					}
					else {
						list.add(activityField.getText(), dependancyField.getText(), Integer.parseInt(durationField.getText()));
						count++;
						activityField.setText("");
						durationField.setText("");
						dependancyField.setText("");
					}
				}
				catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Error: please enter integer for duration.");
				}
			}
			else if (event.getSource() == restartButton) {
				list.deleteList();
				output.setText("");
				JOptionPane.showMessageDialog(null, "PATHFINDER restarted.");
			}
			else if (event.getSource() == processButton) {
				// TODO
				
				try {
//					if(list.isConnected() == false) {
//						String message = "All nodes must be connected. Deleting network diagram...";
//						JOptionPane.showMessageDialog(null, message);
//						list.deleteList();
//					}
//					if(list.endExists() == false) {
//						String message = "There cannot be a cycle. Deleting network diagram...";
//						JOptionPane.showMessageDialog(null, message);
//						list.deleteList();
//					}
//					else {
//						list.dupCount();
//						list.multCount();
//						list.findEnd();
//						Node[][] myArray = new Node[count][count];
//						for(int r = 0; r < count; r++) {
//							for(int c = 0; c < count; c++) {
//								myArray[r][c] = null;
//							}
//						}
//						
//						Node[][] newArray = new Node[count][count];
//						newArray = list.calculate(myArray, count, count);
//						
//						String result = list.makePath(newArray, count, count);
//		   	
//		       	 		output.setText(result);
//					}
					list.dupCount();
					list.multCount();
					list.findEnd();
					Node[][] myArray = new Node[count][count];
					for(int r = 0; r < count; r++) {
						for(int c = 0; c < count; c++) {
							myArray[r][c] = null;
						}
					}
					
					Node[][] newArray = new Node[count][count];
					newArray = list.process(myArray, count, count);
					
					String result = list.makePath(newArray, count, count);
	   	
	       	 		output.setText(result);
				}
				catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Error. Please try again. ");
				}
				
				
			}
			else if (event.getSource() == helpButton) {
				String message = "Activity Name: Enter the name of activity in corresponding text field.\n" + 
						"Activity Duration: Enter amount of time it takes to complete the activity in the corresponding text field.\n" + 
						"Activity Dependencies: Enter the activities that must be completed before you can start the activity being entered. If there are none, enter “0”. \n" +
						"If there are more than one dependencies, add them separately.\n" +
						"Add: Use the data fields name, duration, and dependency to add an entry to the path.\n" + 
						"Delete: Deletes the entry that matches the current information in the activity name data field.\n" + 
						"Process: Processes the possibly paths and the time to complete the entire path. \n" + 
						"Restart: Discards all of the previous paths, allowing you to start a new network diagram.\n" + 
						"?: Help section; you are currently here\n" + 
						"A: Defines the purpose of the program";
				JOptionPane.showMessageDialog(null, message);
			}
			else if (event.getSource() == aboutButton) {
				String message = "The purpose of PATHFINDER is to improve project management \n"
						+ "planning and increase efficiency and accuracy in project planning \n"
						+ "delivery times. Users are able to log activities and expected \n"
						+ "completion times to find the most efficient path to project completion. \n"
						+ "The system itself will take the user inputs, create a \n"
						+ "network diagram to determine all the paths in the network. \n"
						+ "Our team: Beverly Weinnbrener, Rebecca Reyes, Nikki Tran, Jesse Tutor. \n"
						+ "Testing"
						+ "Testing part 2";
				JOptionPane.showMessageDialog(null, message);
			}
			
		}
		
	}
}
