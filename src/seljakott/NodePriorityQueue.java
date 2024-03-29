package seljakott;

/**
 * @author t083851 Jaanus Piip
 * @author t093563 Rahel Rjadnev-Meristo
 *
 */

/**
 * Teise programmeerimistöö modifitseeritud variant, hoiab Node
 */
public class NodePriorityQueue {
	
	private NodeDynamicArray d;
	
	public NodePriorityQueue() {
		super();
		d = new NodeDynamicArray(4);
		d.add(null);
	}
	
	public void enqueue(Node nodeToBeAdded) {
		int location =  d.add(nodeToBeAdded);
		if (d.len() > 1) {
			for (int i = location; i > 1; i /= 2) {
				if (d.get(i).getBound() > d.get((i) / 2).getBound()) {
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
//		System.out.println(d.len());
		if (d.len() < 2) return null;
		d.put(d.rem(),1);
		int i = 1;		
		while (i * 2 <= d.getLastElementIndex()) {
			if (i * 2 + 1 > d.getLastElementIndex() || d.get(i * 2).getBound() > d.get((i * 2) + 1).getBound()) { 
				if (d.get(i).getBound() > d.get(i * 2).getBound()) break;
				changeParentAndChild(i, i * 2);
				i *= 2;
			} else if (d.get(i).getBound() < d.get(i * 2 + 1).getBound()) {
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
		return "NPQ dynarray >>>" + d.toString() + "\n<<< NPQ dynarray lopp";
	}

	public int size() {
		return d.getLastElementIndex() + 1;
	}
}
