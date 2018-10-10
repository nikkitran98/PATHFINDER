// Rebecca
import java.util.*;
public class LinkedList {
	private Node head;		//creates a head of type Node
	private int count=0;
	
	public LinkedList() {		//Default LinkedList constructor
		head = new Node();//creates a new node
		
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
	
	public Node getEnd() {
		Node result = new Node();
		Node temp = head;
		while(temp!= null) {
			if(temp.end == 1 && temp.multiple != -1) {
				if(temp.multiple == 1)
					temp.multiple = -1;
				result = temp;
				break;
			}
			temp = temp.next;
		}
		return result;
	}
	
	public Node getNext(Node nw) {
		Node result = new Node();			//instantiates blank string
		Node temp = head;			//sets a temp node equal to head
		if((nw.name) == null) {
			return result;
		}
		else {
			
		}
		while(temp != null) {		//while temp is not null
			if((nw.dependency).equals(temp.name) && temp.duplicate != -1 && temp.multiple != -1) {		//if the dependency at that node is equal to nw
				if(temp.duplicate == 1)
					temp.duplicate = -1;
				if(temp.multiple == 1)
					temp.multiple = -1;
				result = temp;
			}
			temp = temp.next;
		}
		return result;
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
	
	public void dupCount() {		//changing the duplicate tag to 1 if they have the same dependency
		Node temp = head;
		while(temp.next != null)
		{
			Node ntemp = temp.next;
			while(ntemp!= null)
			{
				if((temp.name).equals(ntemp.name) && temp.dependency != "0") {
					changeDup(temp);
					changeDup(ntemp);
				}
				ntemp = ntemp.next;
			}
			temp = temp.next;
		}
	}
	
	public void changeDup(Node nw) {
		Node temp = head;
		while(temp != null) {
			if((temp.name).equals(nw.dependency))
				temp.duplicate = 1;
			temp = temp.next;
		}
	}
	
	public void deleteList() {
		head = null;
	}
	
	public Node[][] calculate(Node[][] myArray, int rows, int columns){
		for(int r = 0; r < rows; r++) {
			for(int c = 0; c < columns; c++) {
				if(c == 0) {
					myArray[r][0] = getEnd();
				}
				else
					/*if((myArray[r][c-1]).name == null)
						break;
					else */
						myArray[r][c] = getNext(myArray[r][c-1]);
			}
		}
		return myArray;
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
	public void order(ArrayList<Node> list,int total){
		
		Node temp;
		int max=list.get(0).getDuration();
		
		for(int i =0;i<total;i++){
			for(int j=i+1;j<total;j++){
				if(list.get(j).getDuration()>max){
					max=list.get(j).getDuration();
					temp =list.get(i);
					list.set(i,list.get(j));
					list.set(j, temp);
					
				}
			}
			
		}
		
		
	}
	public void printAList(ArrayList<Node> list,int total){
		String result="";
		for(int i =0;i<total;i++)
		System.out.print(list.get(i).getName()+"       "+list.get(i).getDuration()+"\n");
		
		
	}
	public void makePath(Node[][] myArray, int rows, int columns){
		int time = 0;
		int total =0;
		String path = "";
		ArrayList<Node> paths = new ArrayList<Node>();
		Node temp;
		for(int r=0;r<rows;r++){
			if(myArray[r][0].end==0)
				break;
				path = "";
				time=0;
				for(int c=columns-1;c>-1;c--){
					if(myArray[r][c].name==null)
						continue;
					path+=myArray[r][c].name+"/";
					time+=myArray[r][c].duration;
				}
				temp= new Node(path, "", time);
			paths.add(temp);
			total ++;
		}
		order(paths,total);
		printAList(paths,total);
	}
	public void restart(){
		head = new Node();
	}
}
