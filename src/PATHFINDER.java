import java.util.*;
import java.util.List;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class PATHFINDER extends JApplet {
	
	//================================================================================
    // Properties
    //================================================================================
	  private final int WIDTH = 700;
	  private final int HEIGHT = 500;
	  private ControlPanel panel;
	  static LinkedList A;

	//================================================================================
	// Initializer
	//================================================================================
	  public void init() {		  
		  A = new LinkedList();
		  panel = new ControlPanel(A, WIDTH, HEIGHT);
	      
	      getContentPane().add(panel);
	      setSize (WIDTH, HEIGHT);
	  }

	//================================================================================
	// Main 
	//================================================================================
	public static void main (String []args) {
		int duration = 0;
		int count = 0;
		int depCount=0;
		String name = "";
		String dependency = "";
		char input;
		LinkedList A = new LinkedList();
		

		Scanner reader = new Scanner(System.in);

		do {
			printMenu();
			input = reader.next().charAt(0);
			reader.nextLine();

			switch(input) {
			case 'A':
				System.out.print("Enter Activity Name: ");
				name = reader.nextLine();
				System.out.print("Enter Activity Duration: ");
				duration = reader.nextInt();
				reader.nextLine();
				System.out.print("Enter Dependency: ");
				dependency = reader.nextLine();
				String[] depList = dependency.split(",");
				
				for(int i = 0; i < depList.length; i++) {
					A.add(name, depList[i], duration);
				}
				count++;
				break;
			case 'B':
				System.out.print("Enter Activity Name you want to change duration on: ");
				name = reader.nextLine();
				System.out.print("Enter New Activity Duration: ");
				duration = reader.nextInt();
				reader.nextLine();
				A.changeDuration(name, duration);
				System.out.println("duration changed");
				break;	
			case 'C':
				A.multCount();
				A.findEnd();
				
				Node[][] myArray = new Node[count][count];
				for(int r = 0; r < count; r++) {
					for(int c = 0; c < count; c++) {
						myArray[r][c] = null;
					}
				}
				
				Node[][] newArray = new Node[count][count];
				newArray = A.process(myArray, count, count);
				System.out.println(A.makePath(newArray, count, count));
				break;
				
			case 'D':
				A.deleteList();
				System.out.println("All paths have been deleted");
				break;
			case 'E':
				
			case 'R':
				A.deleteList();
				break;
				
			case 'O':
				A.findEnd();
				A.print();
				break;
			case'P':
				System.out.print(A.criticalPath());
				break;

			case 'Q':
				break;
			case 'T':
				String title="";
				String output="";
				try {
				InputStreamReader isr = new InputStreamReader (System.in);
			    BufferedReader stdin = new BufferedReader (isr);
				
				System.out.print("Please enter title for output file");
		        title= stdin.readLine().trim();
				FileWriter fw = new FileWriter (title+".txt");
		        BufferedWriter bw = new BufferedWriter (fw);
		        PrintWriter outFile = new PrintWriter (bw);
		        
		        String result="";
		    	LinkedList alphabatizedLinkedList = new LinkedList();
		    	
		 
		        output+= "Title: "+ title+"\r\n"+alphabatizedLinkedList.alphabatized(A)+A.getOutput();
		        
		         outFile.print(output); 
		        

		        outFile.close();
				
				}
				catch(IOException e)
				{
					e.printStackTrace();
				}
				break;		
			case '?':
				break;
			}
		}while(input != 'Q');
	}

	//================================================================================
    // Print menu
    //================================================================================
	public static void printMenu () {
		System.out.print("Choice\t\tAction\n" +
                "------\t\t------\n" +
                "A\t\tAdd\n" +
                "B\t\tChange Duration\n" +
                "C\t\tCalculate\n" +
                "P\t\tDisplay Critical Path\n" +
                "T\t\tOutput File\n" +
                "D\t\tDelete\n" +
                "R\t\tRestart\n" +
                "O\t\tAbout\n" +
                "Q\t\tQuit\n" +
                "?\t\tDisplay Help\n\n");
	}
}
