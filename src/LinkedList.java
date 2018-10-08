// Rebecca

public class LinkedList {
	private Node head;		//creates a head of type Node
	private int count;
	
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
		count++;
	}
	
	public String getStart() {
		String result = "";
		Node temp = head;
		while(temp != null) {
			if(temp.dependency.equals("0"))
				result = temp.name;
			temp = temp.next;
		}
		return result;
	}
	
	public void printStart()
	{
		
	}
	
	public boolean exists(String name) {	//method to see if the name exists in the linked list
		boolean exists = false;				//sets variable exists to false
		Node temp = head;					//creates a temporary node that is equal to the head
		while(temp.next != null) {			//traverses through the linked list
			if((temp.name).equals(name))	//if the name at that Node is equal to the specified name
				exists = true;				//change exists to true
			temp = temp.next;
		}
		return exists;					//returns the result
	}
	
	public String getNext(String nw) {
		String result = "";
		Node temp = head;
		while(temp != null) {
			if((temp.dependency).equals(nw) && temp.duplicate != -1) {
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
				if((temp.dependency).equals(ntemp.dependency)) {
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
	
	public void calculate(String[][] myArray, int rows, int columns){
		for(int r = 0; r < rows; r++) {
			for(int c = 0; c < columns; c++) {
				if(c == 0) {
					myArray[r][c] = this.getStart();
				}
				else {
					myArray[r][c] = getNext(myArray[r][c-1]);
				}
			}
		}
		
		for(int r = 0; r < rows + 1; r++) {
			if(myArray[r] == myArray[r+1]) {
				myArray[r+1].equals(null);
			}
		}
		
		for(int r = 0; r < rows; r++) {
			for(int c = 0; c < columns; c++) {
				System.out.print(myArray[r][c] + " ");
			}
			System.out.println();
		}
	}
	public void findEnd(){
		Node temp =head;
		Node temp2=head.next;
		for(int i =0;i<count;i++){
			for(int j=0;j<count;j++){
				if(temp2.dependency.equals(temp.name))//checks dependency versus name
					break;
				else if (temp2.name.equals(temp.name))// meaning it looped around to temp without finding a match therefore it is an endpoint. dont return yet incase of clone
				{
					temp.end=1;
			
				}
				else{
				    if(temp2.next==null)
				    	temp2=head;
				    else
				    	temp2=temp2.next;
				}
				
			}
			if(temp.next!=null)
			temp=temp.next;
			if(temp.next!=null)
			temp2=temp.next;
			
		}
		
	}
	public void print(){
		Node temp=head;
		while(temp!=null){
			System.out.print(temp.name+"||");
			temp=temp.next;
		}
		System.out.print("\n");
		temp=head;
		while(temp!=null){
			System.out.print(temp.end+"||");
			temp=temp.next;
		}
		System.out.print("\n");
	}
}
