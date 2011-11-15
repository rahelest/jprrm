package knapsack;

import knapsack.baastyybid.Node;
import knapsack.baastyybid.NodePriorityQueue;

public class JaanuseHargneJaKarbi {
	
	private static NodePriorityQueue PQ;
	private static float maxProfit;
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
			if (v.getBound() > maxProfit) {
				u.setDepth(v.getDepth() + 1);
				u.setWeight(v.getWeight() + weights[u.getDepth()]);
				u.setValue(v.getValue() + values[u.getDepth()]);
				if (u.getWeight() <= sackCapacity && u.getValue() > maxProfit) {
					maxProfit = u.getValue();
				}
				Node temp = new Node(u.getDepth(),u.getValue(),u.getWeight());
				temp.setBound(bound(u));
				if(bound(temp) > maxProfit) {
					PQ.enqueue(u);
				}
				u.setWeight(v.getWeight());
				u.setValue(v.getValue());
				u.setBound(bound(u));
				if (u.getBound() > maxProfit) {
					PQ.enqueue(u);
				}
			}
		}
		
	}
	
	private static void readInputFile() {
		// TODO Auto-generated method stub
		
	}

	private static void initialize() {
		PQ = new NodePriorityQueue();
		maxProfit = 0;
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
