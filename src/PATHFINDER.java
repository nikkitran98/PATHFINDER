import java.util.*;
import javax.swing.*;
import java.awt.*;

public class PATHFINDER extends JApplet {
		private final int WIDTH = 650;
	  private final int HEIGHT = 340;

	  public void init()
	   {
//	       ControlPanel panel = new ControlPanel(WIDTH,HEIGHT);
//	       getContentPane().add(panel);
//	       setSize(WIDTH,HEIGHT);
	       
	       
	       JFrame frame = new JFrame ("PATHFINDER");
	       frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
	       frame.getContentPane().add(new ControlPanel(WIDTH, HEIGHT));
	       frame.pack();
	       frame.setVisible(true);
	  }

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

			switch(input){
			case 'A':
				System.out.print("Enter Activity Name: ");
				name = reader.nextLine();
				System.out.print("Enter Activity Duration: ");
				duration = reader.nextInt();
				reader.nextLine();
				System.out.print("Enter Dependency: ");
				dependency = reader.nextLine();
				A.add(name, dependency, duration);
				
				/*System.out.print("Enter Amount of Dependencies: ");		//WHY DO WE NEED TO COUNT AMOUNT OF DEPENDENCIES NOW?
				depCount=reader.nextInt();		//CONVERSE WITH BEV AND JESSE
				reader.nextLine();
				if (depCount==0){
					dependency= "0";
					A.add(name, dependency, duration);
				}
				else{		//WHAT IF WE DIDN'T HAVE A DEP COUNT AND JUST MADE THEM INPUT MULTIPLE TIMES
					for(int i = 0;i<depCount;i++)
					{
						System.out.print("Enter Activity Dependency #" +(i+1)+ ": ");
						dependency = reader.nextLine();
						A.add(name, dependency, duration);
					}
				}*/
				count++;
				break;
			case 'C':
				A.dupCount();
				A.multCount();
				A.findEnd();
				Node[][] myArray = new Node[count][count];
				for(int r = 0; r < count; r++) {
					for(int c = 0; c < count; c++) {
						myArray[r][c] = null;
					}
				}
				
				Node[][] newArray = new Node[count][count];
				
				newArray = A.calculate(myArray, count, count);
				A.makePath(newArray, count, count);
				
				break;
			case 'D':
				A.deleteList();
				System.out.println("All paths have been deleted");
				break;
			case 'R':
				A.restart();
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
