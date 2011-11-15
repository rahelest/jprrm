package knapsack.baastyybid;

/**
 * @author t083851 Jaanus Piip
 * @author t093563 Rahel Rjadnev-Meristo
 *
 */

public class Node {
	
	private int value;
	private int weight;
	private int profit;
	private float bound;
	
	
	
	public Node () {
		
	}
	
	public Node(int val, Node n) {
		value = val;
		next = n;
	}
	
	public int get() {
		return value;
	}
	
	public Node getNext() {
		return next;
	}

}
