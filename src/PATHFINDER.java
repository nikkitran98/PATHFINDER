import java.util.*;
import javax.swing.*;
import java.awt.*; 

public class PATHFINDER extends JApplet {
		private final int WIDTH = 650;
	  private final int HEIGHT = 340;

	  public void init()
	   {
	       //ControlPanel panel = new ControlPanel(WIDTH,HEIGHT);
	       //getContentPane().add(panel);
	       setSize(WIDTH,HEIGHT);
	   }
	
	public static void main (String []args) {
		int duration = 0;
		int count = 0;
		String name = "";
		String dependency = "";
		char input;
		LinkedList A = new LinkedList();
		
		Scanner reader = new Scanner(System.in);
		
		do {
			printMenu();
			input = reader.next().charAt(0);
			reader.nextLine();
			
			switch(input){
			case 'A':
				System.out.print("Enter Activity Name: ");
				name = reader.nextLine();
				System.out.println("Enter Activity Duration: ");
				duration = reader.nextInt();
				reader.nextLine();
				System.out.println("Enter Activity Dependency: ");
				dependency = reader.nextLine();
				A.add(name, dependency, duration);
				count++;
				
				System.out.println(A.getStart());
				break;
			case 'C':
				A.dupCount();
				String[][] myArray = new String[count][count];
				for(int r = 0; r < count; r++) {
					for(int c = 0; c < count; c++) {
						myArray[r][c] = null;
					}
				}
				
				myArray = A.calculate(myArray, count, count);
				break;
			case 'D':
				A.deleteList();
				System.out.println("All paths have been deleted");
				break;
			case 'R':
				break;
			case 'O':
				break;
			case 'Q':
				break;
			case '?':
				break;
			}
		}while(input != 'Q');
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