package knapsack.tyybid;

import java.util.ArrayList;


/**
 * @author t083851 Jaanus Piip
 * @author t093563 Rahel Rjadnev-Meristo
 *
 */

public class FloatPriorityQueue {

	private FloatDynamicArray d;
	
	public FloatPriorityQueue() {
		setD(new FloatDynamicArray(1));
		getD().add(null);
	}
	
	private FloatPriorityQueue (FloatDynamicArray f) {
		setD(f);
	}
	
//	Lisab arvu x prioriteetj�rjekorda O(lg n)
	public int enqueue(int w, int v) {
		return enqueue(new TreeNode(w, v));
	}
		
	public int enqueue(TreeNode node) {
		int location =  getD().add(node);
		if (getD().lastElement() > 1) { // kui massiivi pikkus on suurem �hest, hakkab programm lapsi otsima
			for (int i = location; i > 1; i /= 2) { //alustades lastest t�idab ts�kkel massiivi tagant ette
				if (getD().get(i).get() > getD().get((i) / 2).get()) {
					TreeNode temp = getD().get(i);
					getD().put(getD().get((i) / 2), i);
					getD().put(temp, (i) / 2);
				} else {
					break;
				}
			}
		}	
		return location;
	}

	public TreeNode dequeue() {
		TreeNode temp = getD().get(1);
		getD().put(getD().rem(),1);
		System.out.println("----------- Eemaldame " + temp.get());
		int i = 1;
		
		while (i * 2 <= getD().lastElement()) {
			if (i * 2 + 1 > getD().lastElement() || getD().get(i * 2).get() > getD().get((i * 2) + 1).get()) { //juhul kui parem laps on puudu v�i on vasak sellest suurem
				if (getD().get(i).get() > getD().get(i * 2).get()) break; //kui laps on vanemast v�iksem, katkestab
				changeParentAndChild(i, i * 2);
				i *= 2;
			} else if (getD().get(i).get() < getD().get(i * 2 + 1).get()) { // juhul kui parem laps on vanemast suurem
				changeParentAndChild(i, i * 2 + 1);
				i = i * 2 + 1;
			} else break;
		}
		return temp;
	}

	/**
	 * See meetod aitab hoida koodi ilusamana ning tegeleb kahe massiivipesa sisu vahetamisega
	 * @param i vanema indeks
	 * @param i2 lapse indeks
	 */
	private void changeParentAndChild(int i, int i2) {
		TreeNode temp = getD().get(i);
		getD().put(getD().get(i2), i);
		getD().put(temp, i2); 
	}
	
	public boolean isEmpty() {
		int temp = getD().lastElement();
		if (temp > 0) return false;
		else return true;
	}
	
	public void toMyString() {
		for (int i = 1; i <= getD().lastElement(); i++) {
			System.out.println("Element nr " + i + " väärtusega " + getD().get(i).get());
		}
	}
	
	public FloatPriorityQueue clone() {
		FloatPriorityQueue f = new FloatPriorityQueue(getD().clone());
		return f;
	}

	public TreeNode getFirst () {
		return getD().get(1);
	}

	public FloatDynamicArray getD() {
		return d;
	}

	public void setD(FloatDynamicArray d) {
		this.d = d;
	}

	public ArrayList<TreeNode> toArrayList() {
		ArrayList<TreeNode> nodes = new ArrayList<TreeNode>();
		FloatPriorityQueue copy = this.clone();
		while (!copy.isEmpty()) {
			nodes.add(copy.dequeue());
		}
		System.out.println("~~~~~~~~~~~ KAMBER: " + nodes);
		return nodes;
	}

//	public int enqueue(TreeNode treeNode) {
//		
//		System.out.println("Enqueueme treeNode: " + treeNode);
//		System.out.println("\nEnne varuenqueue: " + this);
//		int loc = enqueue(treeNode.getWeight(), treeNode.getValue());
//		System.out.println("\tKoht: " + loc + " Vahel varuenqueue: " + this);
//		d.put(treeNode, loc);
//		System.out.println("Pärast varuenqueue: " + this);
//		return loc;
//		
//	}
	public String toString() {
		String result = "" + d.get(0);
		for (int i = 1; i < d.len(); i++) {
			result += ", " + d.get(i);
		}
		return "[" + result + "]";
	}
}
