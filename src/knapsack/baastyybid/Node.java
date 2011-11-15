package knapsack.baastyybid;

/**
 * @author t083851 Jaanus Piip
 * @author t093563 Rahel Rjadnev-Meristo
 *
 */

public class Node {
	
	private int depth;
	private int value;
	private int weight;
	private float profit;
	private float bound;
	
	
	
	public Node () {
		
	}
	
	public Node(int val, Node n) {
		value = val;
	}
	
	public int get() {
		return value;
	}

}
