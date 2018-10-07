// Rebecca

public class LinkedList {
	private Node head;		//creates a head of type Node
	
	public LinkedList() {		//Default LinkedList constructor
		head = new Node();	//creates a new node
	}
	
	public void add(String name, String dependency, int duration) {	//method to add a new node
		if(head == null) {											//checks to see if the linked list is empty
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
	
	public Node getStart() {
		Node result = new Node();
		Node temp = head;
		while(temp != null) {
			if(temp.dependency == null)
				result = temp;
			temp = temp.next;
		}
		return result;
	}
	
	public void delete(String name)				//method to delete a node
	{
		if(exists(name) || head != null)		//checks to see if the name exists in the linked list
		{
			if(head.name == name)				//if the head contains the specified name
				head = head.next;				//move the head to the next, therefor deleting it
			else
			{
				Node temp = new Node();			//creates a temporary Node that is equal to the head
				Node nxt = head.next;			//creates another temporary Node that is equal to the next of the head
				while(nxt.next != null)			//goes through the linked list as long as the next Node is not null
				{
					if(nxt.name == name)		//if the name at that Node is equal to the name we are looking for
					{
						temp = nxt.next;		//make the previous Node equal to the next of the Node we are deleting
					}
					nxt = nxt.next;
				}
			}
		}
		else if(exists(name) == false) {		//error case: the name does not exists
			System.out.println("Invalid Activity Name. Please try again");	//lets the user know it doesn't exist
		}
		else	//error case: the network diagram is empty
			System.out.println("Please add an activity to your network diagram before attempting to delete");	//lets the user know they need to add before trying to delete	
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
	
	public Node getNext(Node nw) {
		Node result = new Node();
		Node temp = head;
		while(temp != head) {
			if(temp.dependency == nw.name && temp.duplicate != -1) {
				if(temp.duplicate == 1)
					temp.duplicate = -1;
				result = temp;
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
}
