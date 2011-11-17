package UusPakk;

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
			if (dynamicArray.length <= i ) {
				add(n);
				return;
			}
			dynamicArray[i] = n;
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
			System.out.println("Vale indeks: " + i + " ning pikkus oli " + dynamicArray.length);
			System.out.println(this.toString());
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
	
	public String toString() {
		String result = "";
		for (int i = 0;  i <= getLastElementIndex(); i++) {
			if (dynamicArray[i] != null)
			result += dynamicArray[i].toString();
		}
		return result;
	}
	
	

	public NodeDynamicArray best() {
		int best = 0;
		NodeDynamicArray bestValikud = new NodeDynamicArray(1);
			
		for (int i = lastElementsIndex;  i > 0; i--) {
//			if (dynamicArray[i] == null) continue;
			int value = dynamicArray[i].getValue();
			if (value > best) {
				best = value;
			}
			if (dynamicArray[i].getValue() >= best) {
				bestValikud.add(dynamicArray[i]);
			}
		}
		
		return bestValikud;
	}
		
	public void set() {
		
		for (int i = 0;  i <= getLastElementIndex(); i++) {
			DynamicArray valikud = dynamicArray[i].getValikud();
			valikud = trim(valikud);
		}
		for (int i = 0;  i < lastElementsIndex; i++) {
			DynamicArray valikud = dynamicArray[i].getValikud();
			for (int j = i + 1; j <= lastElementsIndex; j++) {
				if (dynamicArray[j] == null) System.out.println(toString());
				DynamicArray valikud2 = dynamicArray[j].getValikud();
				if (valikud.vordle(valikud2)) {
					put(rem(), j);
				}
			}
		}
		
	}

	private DynamicArray trim(DynamicArray valikud) {
		System.out.println(valikud);
		for (int i = valikud.lastElementsIndex; i >= 0; i++) {
			if (valikud.get(i) == 1) {
				System.out.print("1");
				break;
			}
			else {
				System.out.print("0");
				valikud.rem();
			}
		}
		System.out.println();
		return valikud;
	}
}


