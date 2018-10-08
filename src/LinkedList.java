// Rebecca

public class LinkedList {
	private Node head;		//creates a head of type Node
	
	public LinkedList() {		//Default LinkedList constructor
		head = new Node();	//creates a new node
	}
	
	public void add(String name, String dependency, int duration) {	//method to add a new node
		if(head.name == null) {											//checks to see if the linked list is empty
			head = new Node(name, dependency, duration);			//if it is empty, add it to the front
		}
		else {
			Node temp = head;										//creates a temporary Node object
			while(temp.next != null) {								//while loop to traverse the linked list
				temp = temp.next;									//moves node to the next
			}
			temp.next = new Node(name, dependency, duration);		//adds the Node to the end
		}
	}
	
	public String getStart() {
		String result = "";
		Node temp = head;
		while(temp != null) {
			if(temp.dependency == null)
				result = temp.name;
			temp = temp.next;
		}
		return result;
	}
	
	public boolean exists(String name) {	//method to see if the name exists in the linked list
		boolean exists = false;				//sets variable exists to false
		Node temp = head;					//creates a temporary node that is equal to the head
		while(temp.next != null) {			//traverses through the linked list
			if(temp.name == name)		//if the name at that Node is equal to the specified name
				exists = true;			//change exists to true
			temp = temp.next;
		}
		return exists;					//returns the result
	}
	
	public String getNext(String nw) {
		String result = "";
		Node temp = head;
		while(temp != null) {
			if(temp.dependency == nw && temp.duplicate != -1) {
				if(temp.duplicate == 1)
					temp.duplicate = -1;
				result = temp.name;
			}
			temp = temp.next;
		}
		return result;
	}
	
	public void dupCount() {
		Node temp = head;
		Node ntemp = temp;
		while(temp != null)
		{
			while(ntemp!= null)
			{
				if(temp.dependency == ntemp.dependency) {
					temp.duplicate = 1;
					ntemp.duplicate = 1;
				}
				ntemp = ntemp.next;
			}
			temp = temp.next;
		}
	}
	
	public void deleteList() {
		head = null;
	}
	
	public String[][] calculate(String[][] myArray, int rows, int columns){
		System.out.println("Hi");
		for(int r = 0; r < rows; r++) {
			for(int c = 0; c < columns; c++) {
				if(c == 0) {
					myArray[r][c] = getStart();
					System.out.print(getStart());
				}
				else {
					System.out.println("Hello");
					myArray[r][c] = getNext(myArray[r][c-1]);
				}
			}
		}
		
		System.out.println("Yo");
		for(int r = 0; r < rows; r++) {
			System.out.print("Hola");
			for(int c = 0; c < columns; c++) {
				System.out.print(myArray[r][c] + " ");
				System.out.println("Hi");
			}
			System.out.println();
		}
		return myArray;
	}
}
