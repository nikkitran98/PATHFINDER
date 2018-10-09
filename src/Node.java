public class Node
{
	int duration;
	int duplicate;
	int end;
	int multiple;
	String name;
	String dependency;
	Node next;
	
	public Node()
	{
		next = null;
		name = "";
		dependency = null;
		duration = 0;
		duplicate = 0;
		multiple = 0;
		end = 0;
	}
	
	public Node(String name2, String dependency2, int duration2)
	{
		next = null;
		name = name2;
		dependency = dependency2;
		duration = duration2;
		duplicate = 0;
		multiple = 0;
	}
}