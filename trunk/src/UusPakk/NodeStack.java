package UusPakk;

public class NodeStack {

	 private Node first; 
	 
	 public void push(Node n) {
	  n.setNext(first);
	  first = n;
	 }
	 
	 public Node pop() throws Exception {
	  if ( first != null) {
	   Node temp = first;
	   first = first.getNext();
	   return temp;
	  } else {
	   throw new Exception();
	  }
	  
	 }
}