package knapsack.baastyybid;

public class NodeDynamicArray {
	
	private Node[] dynamicArray;
	private int lastElementsIndex = -1;

	public NodeDynamicArray(int n) {
		dynamicArray = new Node[n];
	}
	
	public int add(Node n) {
		lastElementsIndex++;
		
		if (lastElementsIndex >= dynamicArray.length) {
			Node[] temp = new Node[dynamicArray.length * 2];
			for (int i = 0; i < dynamicArray.length; i++) {
				temp[i] = dynamicArray[i];
			}
			dynamicArray = temp;
		}
		
		dynamicArray[lastElementsIndex] = n;
		
		return lastElementsIndex;
	}
	
	public Node rem() {
		
		Node tempNode = dynamicArray[lastElementsIndex];
		dynamicArray[lastElementsIndex] = null;
		
		if (lastElementsIndex <= dynamicArray.length / 4) {
			Node[] temp = new Node[dynamicArray.length  / 4];
			for (int i = 0; i < temp.length; i++) {
				temp[i] = dynamicArray[i];
			}
			dynamicArray = temp;
		} else if (lastElementsIndex < 0) {
			return null;
		}
		
		lastElementsIndex--;
		return tempNode;
	}
	
	public Node get(int i) {
		return dynamicArray[i];
	}
	
	public void put(Node n, int i) {
		try {
			dynamicArray[i] = n;
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Nii ei saa, vähendada prooviti väljaspool massiivi!");
		}
	}
	
	public int len() {
		return dynamicArray.length;
	}
	
	public boolean isEmpty() {
		if (lastElementsIndex < 0) return true;
		else return false;
	}

	public int getLastElementIndex() {
		return lastElementsIndex;
	}
}