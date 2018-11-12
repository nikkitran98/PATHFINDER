import java.util.*;
import java.text.*;

public class LinkedList {
	//================================================================================
    // Properties
    //================================================================================
	private Node head;
	private int count = 0;
	private String cPath="";
	private String output="";
	//================================================================================
    // Constructor
    //================================================================================
	public LinkedList() {
		head = new Node();//creates a new node
	}

	//================================================================================
    // Add
    //================================================================================
	public void add(String name, String dependency, int duration) {	//method to add a new node
		if(head.name.equals("")) {											//checks to see if the linked list is empty
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

	//================================================================================
    // Restart
    //================================================================================
	public void deleteList() {
		output="";
		head = new Node();
	}

	//================================================================================
    // Process
    //================================================================================

	public void multCount() {
		Node temp = head;
		while(temp.next != null) {
			Node ntemp = temp.next;
			while(ntemp != null) {
				if((temp.name).equals(ntemp.name)) {
					temp.multiple = 1;
					ntemp.multiple = 1;
				}
				ntemp = ntemp.next;
			}
			temp = temp.next;
		}
	}

	public void findEnd() {
		Node temp =head;
		Node temp2=head.next;

		for(int i =0;i<count;i++) {
			for(int j=0;j<count;j++) {
				if((temp2.dependency).equals(temp.name))//checks dependency versus name
					break;
				// meaning it looped around to temp without finding a match therefore it is an endpoint. dont return yet incase of clone
				else if (temp2.name.equals(temp.name)&&temp2.dependency.equals(temp.dependency)) {
					temp.end=1;
				}
				else {
				    if(temp2.next==null)
				    	temp2=head;
				    else
				    	temp2=temp2.next;
				}
				if(temp.end == 1)
					break;
			}

			if(temp.end == 1) {
				Node change = head;
				while(change != null) {
					if((change.name).equals(temp.name))
						change.end = 1;
					change = change.next;
				}
				break;
			}
			if(temp.next!=null)
			temp=temp.next;
			if(temp.next!=null)
			temp2=temp.next;
			else
			temp2=head;
		}
	}

	public Node[][] process(Node[][] myArray, int rows, int columns) {
		Node temp = head;
		setRotate();
		while(temp != null) {
			updateCount(temp, temp);
			setFork(temp);
			temp = temp.next;
		}
		
		temp = head;
		while(temp != null) {
			if(temp.multiple == 1 && temp.pcount == 0) {
				temp.pcount = (end().pcount)/2;
			}
			if(temp.end == 1 && temp.pcount == 0)
				temp.rotate = 1;
			temp = temp.next;
		}
		
		for(int r = 0; r < rows; r++) {
			for(int c = 0; c < columns; c++) {
				if(c == 0) {
					myArray[r][0] = getEnd();
				}
				else
					if((exists((myArray[r][c-1]).name)))
					{
						myArray[r][c] = getNext(myArray[r][c-1]);
					}
					else 
						break;
			}
			System.out.println();
		}
		return myArray;
	}

	public String makePath(Node[][] myArray, int rows, int columns) {
		int time = 0;
		int total =0;
		String path = "";
		String result = "";
		String dateTime="";
		ArrayList<Node> paths = new ArrayList<Node>();
		Node temp;

		for(int r=0;r<rows;r++) {
			if(myArray[r][0].end==0)
				break;
				path = "";
				time=0;
				for(int c=columns-1;c>=0;c--) {
					try {
						if(exists(myArray[r][c].name)) {
							path+=myArray[r][c].name+"/";
							time+=myArray[r][c].duration;
						}
					}
					catch(NullPointerException e) {
						continue;
					}
				}
			temp= new Node(path, "", time);
			paths.add(temp);
			total++;
		}
		order(paths,total);

		int pathNum = 1;
		for(int i =0;i<total;i++) {
			result += "Path " + pathNum + "		" + paths.get(i).getName()+"       "+paths.get(i).getDuration()+"\r\n";
			pathNum++;
		}
		int maxDur = paths.get(0).getDuration();
		pathNum=1;
		cPath = "";
		for(int i =0;i<total;i++) {
			if( paths.get(i).getDuration()==maxDur){
				cPath += "Path " + pathNum + "		" + paths.get(i).getName()+"       "+paths.get(i).getDuration()+"\n";
				pathNum++;
			}
		}
		//output="";
		dateTime= new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(new Date());
		output+="Created: "+dateTime+"\r\n";
		output+="Paths and duration: \r\n"+result;
		
		//output+=
		return result;
	}


	//================================================================================
    // Intermediate functions
    //================================================================================
	public Node getEnd() {
		Node result = null;
		Node temp = head;
		while(temp!= null) {
			if(temp.end == 1 && temp.multiple != -1) {
				if(temp.multiple == 1 && temp.pcount <= 1)
					temp.multiple = -1;
				else if(temp.multiple == 1 && temp.pcount > 1)
					temp.pcount = temp.pcount - 1;
				else if(temp.multiple == 0 && temp.rotate == 1)
					temp.multiple = -1;
			result = temp;
			break;
			}
			temp = temp.next;
		}
		if(result == null)
			result = new Node();
		return result;
	}

	public Node getNext(Node nw) {
		Node result = null;			//instantiates blank string
		Node temp = head;			//sets a temp node equal to head
		if((nw.name) == null) {
			return result;
		}
		else {
			while(temp != null) {		//while temp is not null
				if((nw.dependency).equals(temp.name) && temp.multiple != -1) {		//if the dependency at that node is equal to nw
					if(result == null) {
						if(temp.multiple == 1 && temp.rotate == 1 || temp.multiple == 0) {
							if(temp.rotate == 1) {
								temp.rotate = 0;
								if(temp.pcount == 1)
									temp.multiple = -1;
								else if (temp.pcount > 1)
									temp.pcount = temp.pcount - 1;
							}
							result = temp;
						}
						else if(temp.rotate == 0)
								temp.rotate = 1;
					}
					else if(temp.rotate == 0)
						temp.rotate = 1;
				}
			temp = temp.next;
			}
		}
		if (result == null)
			result = new Node();
		return result;
	}


	public boolean exists(String dependency) {			//method to see if the name exists in the linked list
		boolean exists = false;							//sets variable exists to false
		Node temp = head;								//creates a temporary node that is equal to the head
		while(temp != null) {							//traverses through the linked list
			if((temp.name).equals(dependency) == true)	//if the name at that Node is equal to the specified name
				exists = true;							//sets exists to true
			temp = temp.next;
		}

		if(dependency == "0") {
			temp = head;
			while(temp != null) {
				if(temp.dependency == dependency) {
					exists = true;
				}
			}
		}
		return exists;						//returns the result
	}

	public void updateCount(Node current, Node nw) {
		Node temp = head;
		String name = nw.dependency;
		
		while(temp != null) {
			if(temp.name.equals(name) && temp.multiple == 1 && nw.multiple == 1)
			{
				Node ntemp = temp.next;
				while(ntemp != null) {
					if(ntemp.name.equals(name)) {
						current.pcount = current.pcount + 2;
						updateCount(current, ntemp);
						updateCount(current, temp);
					}
					ntemp = ntemp.next;
				}
			}
			else if(temp.name.equals(name))
				updateCount(current,temp);
			temp = temp.next;
		}
	}

	public void print() {
		Node temp=head;

		while(temp!=null) {
			System.out.print(temp.name+"||");
			temp=temp.next;
		}

		System.out.print("\n");
		temp=head;

		while(temp!=null) {
			System.out.print(temp.end+"||");
			temp=temp.next;
		}
		System.out.print("\n");
	}

	public void order(ArrayList<Node> list,int total) {
		Node temp;

		for(int i =0;i<total;i++) {
			for(int j=i+1;j<total;j++) {
				if(list.get(j).getDuration()>list.get(i).getDuration()) {
					temp =list.get(i);
					list.set(i,list.get(j));
					list.set(j, temp);
				}
			}
		}
	}
	
	public void setRotate() {
		Node temp = head;
		while(temp != null) {
			if(temp.multiple == 1 && temp.end != 1) 
				temp.rotate = 1;
			temp = temp.next;
		}
	}
	
	public void setFork(Node node) {		//node should be a multiple
		int fork = 0;
		Node temp = head;
		while(temp != null) {
			if(temp.dependency.equals(node.name) && node.pcount > 0)
				fork++;
			temp = temp.next;
		}
		
		if(fork > 1) {
			node.pcount = node.pcount + fork;
		}
	}
	
	public Node end() {
		Node result = new Node();
		Node temp = head;
		while(temp != null) {
			if(temp.end == 1)
				result = temp;
			temp = temp.next;
		}
		return result;
	}
	
	//================================================================================
    // Error checking
    //================================================================================

	// checks to see if the all the nodes are connected to each other
	public boolean isConnected() {
		boolean result = false;
		int endCount = 0;
		Node temp = head;

		// test case 1: if a node doesn't depend on anything, other
		// nodes must depend on it assuming it is the starter node
		while(temp != null) {
			// check if it depends on nothing first
			if (temp.dependency == "0") {
				// if so, checks to see if something depends on it
				result = isDependedOn(temp.name);
				if(result == false)
					break;
			}
			temp = temp.next;
		}

		// test case 2: a node must depend on something and something
		// must depend on it

		// test case 2: there cannot be multiple ends or else it's
		// not fully connected

//		if(result) {
//			temp = head;
//			while(temp != null) {
//				if (exists(temp.dependency) && dependent(temp.name))
//					result = true;
//				if(result == false)
//					break;
//				else
//					temp = temp.next;
//			}
//
//		}

		if(result) {
			temp = head;
			findEnd();
			while(temp != null) {
				if (temp.end == 1) {
					endCount++;
				}
				temp = temp.next;
			}
			// checks to see if there are multiple ends
			if (endCount > 1) {
				result = false;
			}
			else
				result = true;
		}
		return result;
	}

	// checks if another node depends on the dependee
	public boolean isDependedOn(String dependee) {
		boolean dependent = false;
		Node temp = head;

		while(temp != null) {
			if ((temp.dependency).equals(dependee)) {
				dependent = true;
				break;
			}
			temp = temp.next;
		}
		return dependent;
	}

	public boolean endExists() {
		boolean result = false;
		Node temp = head;
		findEnd();
		while(temp != null) {
			if(temp.end == 1) {
				result = true;
				break;
			}
			temp=temp.next;
		}
		return result;
	}
	
	public String criticalPath(){
		return cPath;
	}
	
	public void changeDuration(String name, int newDuration)
	{
		Node temp = head;
		while(temp !=null)
		{
			if (temp.name.equals(name))
				temp.duration=newDuration;
			temp=temp.next;

		}
	}
	public String getOutput(){
		return output;
	}
	public boolean isRepeated(Node checkNode)
	{
		Node startNode = head;
		boolean found = false;
		while(startNode != null)
		{
			if (startNode.name.equals(checkNode.name))
			{
				found = true;
				break;
			}
			else
			{
				startNode = startNode.next;
			}
		}
		return found;
	}
	
public String alphabatized(LinkedList original)
{

	// setting up to add node
	String result="";
	//LinkedList alphabatizedLinkedList = new LinkedList();
	Node current = head;
	Node previous = head;
	Node iterator = original.head;
	// iterate through current linked list
	// turn each node into a temp node
	while(iterator!=null)
	{
		Node temp = new Node();
		temp.name=iterator.getName();
		temp.duration=iterator.getDuration();
		//alphabatizedLinkedList.head;
		if (isRepeated(temp))
		{
		// go to the next node in our current linked list
		// do not add to our alphabatizedLinkedList
			iterator=iterator.next;
		}
		// add to list in order
		else
		{
			// If there are no node in the list
			if (head.name.equals(""))
			{
				head = temp;
				head.next = null;
			}
			// Adds if node needs to go in front of the 1st node
			
			else if ((head.name.compareTo(temp.name)) > 0)
			{
				temp.next = head;
				head = temp;
			}
			// Adds in all other situations
			else
			{
				current = head;
				previous = head;
				// Increment current
				//current = current.next;
				// Goes to end of linked list
				while(current != null)
				{
					current = current.next;
					// Adding if we are at the end of the list
					if (current == null)
					{
						previous.next = temp;
						temp.next = null;
						break;
					}
					// Adds to the middle of the list
					else if (current.name.compareTo(temp.name) > 0)
					{
						temp.next = current;
						previous.next = temp;
						break;
					}
					else
					{
						previous = previous.next;
					}
				}
			}
			iterator=iterator.next;
		}
		
	}
	Node temp2 = head;
	result+="Activities in ALphabetic Order:\r\n";
	while(temp2!=null)
	{
		result+= "Activity Name: "+temp2.name+"Duration: "+temp2.duration+"\r\n";
		temp2=temp2.next;
	}
	print();
	return result;
}
	//DELETE
	public void dupCount() {
		
	}
	
}
