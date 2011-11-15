package knapsack.baastyybid;

public class NodePriorityQueue extends PriorityQueue {
	
	private NodeDynamicArray d;
	
	public NodePriorityQueue() {
		super();
	}
	
	public void enqueue(Node nodeToBeAdded) {
		int location =  d.add(nodeToBeAdded);
		if (d.len() > 1) {
			for (int i = location; i > 0; i /= 2) {
				if (d.get(i) < d.get((i) / 2)) {
					int temp = d.get(i);
					d.put(d.get((i) / 2), i);
					d.put(temp, (i) / 2);
				} else {
					break;
				}
			}
		}
	}
	
	public Node dequeue() {
		Node temp = d.get(1);
		d.put(d.rem(),1);
		int i = 1;		
		while (i * 2 <= d.lastElement()) {
			if (i * 2 + 1 > d.lastElement() || d.get(i * 2) < d.get((i * 2) + 1)) { 
				if (d.get(i) < d.get(i * 2)) break;
				changeParentAndChild(i, i * 2);
				i *= 2;
			} else if (d.get(i) > d.get(i * 2 + 1)) {
				changeParentAndChild(i, i * 2 + 1);
				i = i * 2 + 1;
			} else break;
		}		
		toMyString();
		return temp;
	}
}
