import java.util.*;
import javax.swing.*;
import java.awt.*; 

public class PATHFINDER extends JApplet {
	public void init () {
		Container content = getContentPane();
		
		Button addButton = new Button("Add");
		Button restartButton = new Button("Restart");
		Button calcButton = new Button("Calculate");
		Button helpButton = new Button("?");
		Button aboutButton = new Button("A");
		
		content.add(addButton);
		content.add(restartButton);
		content.add(calcButton);
		content.add(helpButton);
		content.add(aboutButton);
	}
	
	public static void main (String []args) {
		printMenu();
	}
	
	public static void printMenu () {
		System.out.print("Choice\t\tAction\n" +
                "------\t\t------\n" +
                "A\t\tAdd\n" +
                "C\t\tCalculate\n" +
                "D\t\tDelete\n" +
                "R\t\tRestart\n" +
                "O\t\tAbout\n" +
                "Q\t\tQuit\n" +
                "?\t\tDisplay Help\n\n");
	}
}