package UusPakk;
/**
 * @author t083851 Jaanus Piip
 * @author t093563 Rahel Rjadnev-Meristo
 *
 */

public class DynamicArray {

	protected int[] dynArray;
	protected int lastElementsIndex = -1;
	
	public DynamicArray(int n) {
		dynArray = new int[n];
	}
	
//	public DynamicArray create(int n) {
//		dynArray = new int[n];
//		return this;
//	}
	
	public int add(int x) {
		lastElementsIndex++;
		if (lastElementsIndex >= dynArray.length) {
			int[] temp = new int[dynArray.length * 2];
			for (int i = 0; i < dynArray.length; i++) {
				temp[i] = dynArray[i];
			}
//			for (int i = 0; i < temp.length; i++) {
////				System.out.println(temp[i]);
//			}
			dynArray = temp;
			
//			System.out.println(x + " 2");
		}
		dynArray[lastElementsIndex] = x;
		
//		System.out.println("Lisasin v��rtuse: " + x + " lastElementsNo: " + lastElementsNo);
		return lastElementsIndex;
	}
	
	public int rem() {		
		int temp = dynArray[lastElementsIndex];
		dynArray[lastElementsIndex] = 0;
		if (lastElementsIndex <= dynArray.length / 4) {
			int[] temp2 = new int[dynArray.length  / 4];
			for (int i = 0; i < temp2.length; i++) {
				temp2[i] = dynArray[i];
			}
			dynArray = temp2;
		} else if (lastElementsIndex < 0) {
			return 0;
		}
//		System.out.println(temp + " " + lastElementsNo);
		
		lastElementsIndex--;
		return temp;
	}
	
	public int get(int i) {
		return dynArray[i];
	}
	
	public void put(int x, int i) {
		try {
			dynArray[i] = x;
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Nii ei saa, v�hendada prooviti v�ljaspool massiivi!");
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
}
