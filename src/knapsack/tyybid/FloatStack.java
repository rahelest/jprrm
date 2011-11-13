package knapsack.tyybid;

/**
 * @author t083851 Jaanus Piip
 * @author t093563 Rahel Rjadnev-Meristo
 *
 */

public class FloatStack {
	
	private FloatNode first; 
	
	public FloatStack() {}
	
	public void create() {}
	
	public void push(int x) {
		first = new FloatNode(x, first);
	}
	
	public float pop() throws Exception {
		if ( first != null) {
			float temp = first.get();
			first = first.getNext();
			return temp;
		} else {
			throw new Exception();
		}
		
	}
	
	public boolean isEmpty() {
		if (first == null) {
			return true;
		} else {
			return false;
		}
	}
	
}
