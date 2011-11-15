package knapsack.baastyybid;

public class NodePriorityQueue extends PriorityQueue {
	
	private NodeDynamicArray dynArray;
	
	public NodePriorityQueue() {
		super();
	}
	
	public void enqueue(Node nodeToBeAdded) {
		int location =  dynArray.add(nodeToBeAdded);
		if (dynArray.len() > 1) {
	}

}
