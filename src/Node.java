public class Node
{
	Node next;
	String name;
	String dependency;
	int duration;
	
	public Node()
	{
		next = null;
		name = null;
		dependency = null;
		duration = 0;
	}
	
	public Node(String name2, String dependency2, int duration2)
	{
		next = null;
		name = name2;
		dependency = dependency2;
		duration = duration2;
	}
}