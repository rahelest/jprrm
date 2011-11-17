package UusPakk;

public class NodeStack {

	 private Node first; 
	 
	 public void push(Node n) {
	  n.setNext(first);
	  first = n;
	 }
	 
	 public Node pop() {
	  if ( first != null) {
	   Node temp = first;
	   first = first.getNext();
	   return temp;
	  } else {
	  return null;
	  }
	  
	 }

	public boolean isEmpty() {
		if (first == null) return true;
		else return false;
	}
}