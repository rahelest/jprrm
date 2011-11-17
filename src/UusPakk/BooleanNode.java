package UusPakk;

public class BooleanNode {
	
	private BooleanNode next;
	private boolean value;

	public BooleanNode(boolean b) {
		value = b;
	}

	public BooleanNode getNext() {
		return next;
	}

	public void setNext(BooleanNode booleanNode) {
		next = booleanNode;
		
	}
	
	public boolean getValue() {
		return value;
	}

}
