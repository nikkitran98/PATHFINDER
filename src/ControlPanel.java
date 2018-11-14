import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.event.*;

public class ControlPanel extends JPanel {
	
	//================================================================================
    // Properties
    //================================================================================
	private JPanel topPanel, top1, top2, top3, activityPanel, durationPanel, dependancyPanel, bottomPanel;
	private JLabel activityLabel, durationLabel, dependancyLabel;
	private JTextField activityField, durationField, dependancyField;
	private JButton addButton, restartButton, processButton;
	private JButton helpButton, aboutButton;
	private JButton criticalButton, changeButton, reportButton;
	private JScrollPane scrollPane;
	private JSplitPane pane;
	private int width, height;
	private String activity, dependancy;
	private int duration, count;
	private LinkedList list;
	private JTextArea output;
	
	ButtonListener buttonlistener = new ButtonListener();
	
	//================================================================================
    // Constructor
    //================================================================================
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
		top3 = new JPanel();
		activityPanel = new JPanel();
		durationPanel = new JPanel();
		dependancyPanel = new JPanel();
		bottomPanel = new JPanel();
		
		activityLabel = new JLabel("Activity Name: ");
		durationLabel = new JLabel("Activity Duration: ");
		dependancyLabel = new JLabel("Activity Dependencies: ");
		
		output = new JTextArea (15,56);
		
		scrollPane= new JScrollPane(output);
		
		activityField = new JTextField();
		durationField = new JTextField();
		dependancyField = new JTextField();
		
		addButton = new JButton("Add");
		restartButton = new JButton("Restart");
		processButton = new JButton("Process");
		helpButton = new JButton("?");
		aboutButton = new JButton("A");
		criticalButton = new JButton("Display critical path");
		changeButton = new JButton("Change duration");
		reportButton = new JButton("Create report");
		
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
		
		top3.setLayout(new GridLayout(1, 3));
		top3.add(criticalButton);
		top3.add(changeButton);
		top3.add(reportButton);
		
		topPanel.setLayout(new GridLayout(3,1));
		topPanel.add(top1);
		topPanel.add(top2);
		topPanel.add(top3);
		
		bottomPanel.add(scrollPane);
		
		addButton.addActionListener(buttonlistener);
		restartButton.addActionListener(buttonlistener);
		processButton.addActionListener(buttonlistener);
		helpButton.addActionListener(buttonlistener);
		aboutButton.addActionListener(buttonlistener);
		criticalButton.addActionListener(buttonlistener);
		changeButton.addActionListener(buttonlistener);
		reportButton.addActionListener(buttonlistener);
		
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

		pane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, topPanel, bottomPanel);
		add(pane);
	}

	//================================================================================
    // Listener
    //================================================================================
	private class ButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent event) {
			
			if(event.getSource() == addButton) {
		
				try {
					if(activityField.getText().isEmpty() || dependancyField.getText().isEmpty() || durationField.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Error: please make sure all fields are filled.");
					} else {
						String[] depList = dependancyField.getText().split(",");
						
						for(int i = 0; i < depList.length; i++) {
							list.add(activityField.getText(), depList[i], Integer.parseInt(durationField.getText()));
						}
						
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
				try {
					list.findEnd();
					
					if(!list.endExists()) {
						String message = "There cannot be a cycle. Deleting network diagram...";
						JOptionPane.showMessageDialog(null, message);
						list.deleteList();
					}
					else if(!list.isConnected()) {
						String message = "All nodes must be connected. Deleting network diagram...";
						JOptionPane.showMessageDialog(null, message);
						list.deleteList();
					}
					else {
						String s = "Paths\t\t" + "Path Dependencies\t\t" + "Duration\n";
						list.resetFLag();
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
		   	
						s += result;
		       	 		output.setText(s);
					}
				}
				catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Error. Please try again. ");
				}
				
				
			}
			else if (event.getSource() == criticalButton) {
				String s = "Critical Path(s)\t\t" + "Duration\n" + list.criticalPath();
				output.setText(s);
			}
			else if (event.getSource() == changeButton) {
				JTextField field1 = new JTextField();
				JTextField field2 = new JTextField();
				
				Object[] fields = {
						"Enter activity to be changed:", field1,
						"Duration:", field2
				};
				
				JOptionPane.showConfirmDialog(null, fields, "", JOptionPane.OK_CANCEL_OPTION);
				
				list.changeDuration(field1.getText(), Integer.parseInt(field2.getText()));
			}
			else if (event.getSource() == reportButton) {
				String title = "";
				String output="";
				String dateTime="";
				dateTime= new SimpleDateFormat("MM.dd.yyyy HH:mm:ss").format(new Date());
				title = JOptionPane.showInputDialog("Name of the report:", JOptionPane.OK_CANCEL_OPTION);
				
				try {
				FileWriter fw = new FileWriter (title+".txt");
		        BufferedWriter bw = new BufferedWriter (fw);
		        PrintWriter pw = new PrintWriter (bw);
		        
		    	LinkedList alphabatizedLinkedList = new LinkedList();
		    	
		 
		        output+= "Title: "+ title+"\r\n\r\n"+"Created: "+dateTime+"\r\n\r\n"+alphabatizedLinkedList.alphabatized(list)+list.getOutput();
		        pw.print(output); 
		        pw.close();
				
				} catch(IOException e) {
					e.printStackTrace();
				}
				
			}
			else if (event.getSource() == helpButton) {
				String message = "Activity Name: Enter the name of activity in corresponding text field.\n" + 
						"Activity Duration: Enter amount of time it takes to complete the activity in the corresponding text field.\n" + 
						"Activity Dependencies: Enter the activities that must be completed before you can start the activity being entered. If there are none, enter “0”. \n" +
						"If there are more than one dependencies, seperate them with a “,”.\n" +
						"Add: Uses the data fields name, duration, and dependency to add an entry to the path.\n" + 
						"Process: Processes the possible paths and the time to complete each path. \n" + 
						"Restart: Discards all of the previous paths and activities, allowing you to start a new network diagram.\n" + 
						"?: Help section. You are currently here.\n" + 
						"A: Defines the purpose of the program.\n"+
						"Display crtical path: Output critical path(s).\n"+
						"Change duration: Change duration of an activity.\n"+
						"Creat report: Create text file containing network diagram information with given title.";
				JOptionPane.showMessageDialog(null, message);
			}
			else if (event.getSource() == aboutButton) {
				String message = "The purpose of PATHFINDER is to improve project management \n"
						+ "planning and increase efficiency and accuracy in project planning \n"
						+ "delivery times. Users are able to log activities and expected \n"
						+ "completion times to find the most efficient path to project completion. \n"
						+ "The system itself will take the user inputs, create a \n"
						+ "network diagram to determine all the paths in the network. \n"
						+ "Our team: Beverly Weinnbrener, Rebecca Reyes, Nikki Tran, Jesse Tutor. \n";
				JOptionPane.showMessageDialog(null, message);
			}
			
		}
		
	}
}
