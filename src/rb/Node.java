package rb;

public class Node 
{
  public int val;
  public Node left;
  public Node right;
  public Node p;
  public boolean color;
  
  public Node(int newval) 
  {
    val = newval;
    left = Tree_NIL();
    right = Tree_NIL();
    p = Tree_NIL();
    color = true;
  }
  
  public Node()
  {
  	val = 0;
    left = null;
    right = null;
    p = null;
    color = false;
  }
  
  public static Node Tree_NIL()
  {
  	return new Node();
  }	
}