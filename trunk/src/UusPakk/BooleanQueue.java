package UusPakk;

public class BooleanQueue {
	private BooleanNode first;
	private BooleanNode last;
	
	private int size;
	
	public BooleanQueue (BooleanNode f) {
		first = f;
		last = f;
		size = 1;
	}
	
	public BooleanQueue (BooleanNode f, BooleanNode l, int s) {
		first = f;
		last = l;
		size = s;
	}
	
	public int add (boolean b) {
		last.setNext(new BooleanNode(b));
		last = last.getNext();
		size++;
		return size - 1;
	}
	
	public BooleanQueue clone() {
		return new BooleanQueue(first, last, size);
	}
	
	public BooleanNode get(int index) {
		int i = 0;
		BooleanNode node = first;
		while (i < index && i < size ) {
			node = node.getNext();
			i++;
		}
		return node;
	}
	
	

}
