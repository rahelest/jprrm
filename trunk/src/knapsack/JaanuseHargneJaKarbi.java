package knapsack;

import knapsack.baastyybid.Node;
import knapsack.baastyybid.NodePriorityQueue;

public class JaanuseHargneJaKarbi {
	
	private static NodePriorityQueue PQ;
	private static float maxprofit;
	private static Node u;
	private static Node v;
	
	public static void main(String[] args) {
		initialize();			
		
	}
	
	private static void initialize() {
		PQ = new NodePriorityQueue();
		maxprofit = 0;
		u = new Node();
		v = new Node();
		v.setDepth(0);
		v.setProfit(0);
		v.setWeight(0);		
	}
	
	private static float bound(Node input) {
		
		
		
		return result;		
	}
	

}
