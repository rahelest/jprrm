package knapsack.tyybid;

import knapsack.Hargne_karbi2;

public class TreeNode {

	private float ratio;
	private int value;
	private int weight;
	private int depth;
	private boolean[] elements;
	private FloatNode next;

	public TreeNode (int v, int w, int d, boolean[] ele) {
		ratio = ((float) v) / ((float) w);
		if (v == 0 && w == 0) {
			ratio = 0;
		}
		value = v;
		weight = w;
		depth = d;
		elements = ele.clone();
//		System.out.println(Hargne_karbi2.arrayElements(elements));
	}

	public TreeNode(int w, int v) {
		ratio = ((float) v) / ((float) w);
		if (v == 0 && w == 0) {
			ratio = 0;
		}
		value = v;
		weight = w;
	}

	public float get() {
		return ratio;
	}

	public int getValue() {
		return value;
	}

	public int getWeight() {
		return weight;
	}

	public int getDepth() {
		return depth;
	}

	public boolean[] getElements() {
//		System.out.println(Hargne_karbi2.arrayElements(elements));
		return elements;
	}

	public FloatNode getNext() {
		return next;
	}
	
	public String toString() {
		String ele = "";
//		if (elements != null) ele =  Hargne_karbi2.arrayElements(elements);
		return ratio  + " " + ele;
	}
	
}
