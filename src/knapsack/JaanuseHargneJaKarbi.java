package knapsack;

import knapsack.baastyybid.Node;
import knapsack.baastyybid.NodePriorityQueue;

public class JaanuseHargneJaKarbi {
	
	private static NodePriorityQueue PQ;
	private static float maxprofit;
	private static Node u;
	private static Node v;
	private static int[] values;
	private static int[] weights;
	private static int sackCapacity;
	private static int itemCount;
	
	
	public static void main(String[] args) {
		readInputFile();
		initialize();
		while (!PQ.isEmpty()) {
			v = PQ.dequeue();
			if (v.getBound() > maxprofit) {
				u.setDepth(v.getDepth() + 1);
				u.setWeight(v.getWeight() + weights[u.getDepth()]);
				u.setValue(v.getValue() + values[u.getDepth()]);
				
			}
		}
		
	}
	
	private static void readInputFile() {
		// TODO Auto-generated method stub
		
	}

	private static void initialize() {
		PQ = new NodePriorityQueue();
		maxprofit = 0;
		u = new Node();
		v = new Node();
		v.setDepth(0);
		v.setValue(0);
		v.setWeight(0);
		v.setBound(bound(v));
		PQ.enqueue(v);
	}
	
	private static float bound(Node input) {
		float result = 0;
		
		
		return result;		
	}
	

}
