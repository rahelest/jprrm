package knapsack.tyybid;


/**
 * @author t083851 Jaanus Piip
 * @author t093563 Rahel Rjadnev-Meristo
 *
 */

public class FloatDynamicArray {

	private TreeNode[] dynArray;
	private int lastElementsIndex = -1;
	
	public FloatDynamicArray(int n) {
		dynArray = new TreeNode[n];
	}
	
	public int add(TreeNode f) {
		
		lastElementsIndex++;
		if (lastElementsIndex >= dynArray.length) {
			TreeNode[] temp = new TreeNode[dynArray.length * 2];
			for (int i = 0; i < dynArray.length; i++) {
				temp[i] = dynArray[i];
			}
			dynArray = temp;

		}
//		System.out.println(lastElementsIndex + " " + dynArray.length);
		dynArray[lastElementsIndex] = f;

		return lastElementsIndex;

	}
	
	
	public int add(int w, int v) {
		return add(new TreeNode(w, v));
	}
	
	
//	private int add(int w, int v) {
//		lastElementsIndex++;
//		if (lastElementsIndex >= dynArray.length) {
//			TreeNode[] temp = new TreeNode[dynArray.length * 2];
//			for (int i = 0; i < dynArray.length; i++) {
//				temp[i] = dynArray[i];
//			}
////			for (int i = 0; i < temp.length; i++) {
//////				System.out.println(temp[i]);
////			}
//			dynArray = temp;
//			
////			System.out.println(x + " 2");
//		}
//		dynArray[lastElementsIndex] = new TreeNode(w, v);
//		
////		System.out.println("Lisasin v��rtuse: " + w + " ja " + v + " lastElementsIndex: " + lastElementsIndex);
//		return lastElementsIndex;
//	}
	TreeNode rem() {		
		TreeNode temp = dynArray[lastElementsIndex];
		dynArray[lastElementsIndex] = null;
		if (lastElementsIndex <= dynArray.length / 4) {
			TreeNode[] temp2 = new TreeNode[dynArray.length  / 4];
			for (int i = 0; i < temp2.length; i++) {
				temp2[i] = dynArray[i];
			}
			dynArray = temp2;
		} else if (lastElementsIndex < 0) {
			return null;
		}
//		System.out.println(temp + " " + lastElementsNo);
		
		lastElementsIndex--;
		return temp;
	}
	
	public TreeNode get(int i) {
//		if (dynArray[i] == null) System.out.println("NULL");
		return dynArray[i];
	}
	
	public void put(int w, int v, int i) {
		try {
			dynArray[i] = new TreeNode (w, v);
		} catch (IndexOutOfBoundsException e) {
			new RuntimeException("IndexOutOfBoundsss!");
		}
	}
	
	public void put(TreeNode f, int i) {
		try {
			dynArray[i] = f;
		} catch (IndexOutOfBoundsException e) {
			new RuntimeException("IndexOutOfBoundsss!");
		}
	}
	
	public int len() {
		return dynArray.length;
	}
	
	public boolean isEmpty() {
		if (lastElementsIndex < 0) return true;
		else return false;
	}

	public int lastElement() {
		return lastElementsIndex;
	}
	
	public FloatDynamicArray clone() {
		FloatDynamicArray f = new FloatDynamicArray(1);
		f.add(null);
		int i = 0;
		while ( ++i < dynArray.length && dynArray[i] != null ) {
			f.add(dynArray[i].getWeight(), dynArray[i].getValue());
		}
//		System.out.println(f.len());
		return f;
		
	}
}
