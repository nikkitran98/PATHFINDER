public class Node {
	
	//================================================================================
    // Properties
    //================================================================================
	
	int duration, duplicate, end, multiple, pcount, rotate;
	String name, dependency;
	Node next;
	
	//================================================================================
    // Base constructor
    //================================================================================
	public Node() {
		next = null;
		name = null;
		dependency = null;
		duration = 0;
		duplicate = 0;			//the case where two nodes have the same dependency
		multiple = 0;			//checks to see if there are many of one activity
		pcount = 0;				//checks to see the path count for that activity
		end = 0;				//checks to see if it is the end
		rotate = 1;
	}
	
	//================================================================================
    // Constructor
    //================================================================================
	public Node(String name2, String dependency2, int duration2) {
		next = null;
		name = name2;
		dependency = dependency2;
		duration = duration2;
		duplicate = 0;
		multiple = 0;
		pcount = 0;
	}
	
	//================================================================================
    // Getters
    //================================================================================
	
	public int getDuration() {
		return duration;
	}
	
	public String getName() {
		return name;
	}
}