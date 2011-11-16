package UusPakk;

public class NodeStack {
	 private Node first; 
	 
	 public NodeStack() {}
	 
	 public void create() {}
	 
	 public void push(Node node) {
		 Node temp = first;
		 first = node;
		 first.setNext(temp);
	 }
	 
	 public Node pop() throws Exception {
		 if ( first != null) {
			 Node temp = first.getNode();
			 first = first.getNext();
			 return temp;
		 } else {
			 throw new Exception();
		 }
	  
	 }
	 
	 public boolean isEmpty() {
		 if (first == null) {
			 return true;
		 } else {
			 return false;
		 }
	 }
}
