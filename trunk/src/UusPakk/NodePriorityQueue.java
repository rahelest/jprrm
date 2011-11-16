package UusPakk;

public class NodePriorityQueue {
	
	private NodeDynamicArray d;
	
	public NodePriorityQueue() {
		super();
		d = new NodeDynamicArray(1);
	}
	
	public void enqueue(Node nodeToBeAdded) {
		int location =  d.add(nodeToBeAdded);
		if (d.len() > 1) {
			for (int i = location; i > 0; i /= 2) {
				if (d.get(i).getBound() < d.get((i) / 2).getBound()) {
					Node temp = d.get(i);
					d.put(d.get((i) / 2), i);
					d.put(temp, (i) / 2);
				} else {
					break;
				}
			}
		}
	}
	
	public Node dequeueNode() {
		Node temp = d.get(1);
		d.put(d.rem(),1);
		int i = 1;		
		while (i * 2 <= d.getLastElementIndex()) {
			if (i * 2 + 1 > d.getLastElementIndex() || d.get(i * 2).getBound() < d.get((i * 2) + 1).getBound()) { 
				if (d.get(i).getBound() < d.get(i * 2).getBound()) break;
				changeParentAndChild(i, i * 2);
				i *= 2;
			} else if (d.get(i).getBound() > d.get(i * 2 + 1).getBound()) {
				changeParentAndChild(i, i * 2 + 1);
				i = i * 2 + 1;
			} else break;
		}
		return temp;
	}
	
	public boolean isEmpty() {
		int temp = d.getLastElementIndex();
		if (temp > 0) return false;
		else return true;
	}
	
	protected void changeParentAndChild(int i, int i2) {
		Node temp = d.get(i);
		d.put(d.get(i2), i);
		d.put(temp, i2); 
	}
	
	public String toString() {
		return "NPQ dynarray:" + d.toString() + "\nNPQ dynarray lopp";
	}
}
