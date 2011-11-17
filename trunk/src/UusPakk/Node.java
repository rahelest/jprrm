package UusPakk;

import java.util.ArrayList;

/**
 * @author t083851 Jaanus Piip
 * @author t093563 Rahel Rjadnev-Meristo
 *
 */

public class Node {
	
	private int depth;
	private int value;
	private int weight;
	private float bound;
	private float ratio;
	private Node next;
	private ArrayList<Boolean> valikud;
	
	public Node() {
		depth = -1;
		value = 0;
		weight = 0;
		bound = 0;
		ratio = 0;
	}
	
	public Node (int newDepth, int newValue, int newWeight) {
		depth = newDepth;
		value = newValue;
		weight = newWeight;
		bound = 0;
		if (weight == 0) {
			ratio = 0;
		} else {
			ratio = ((float) value) / ((float) weight);
		}
	}
	
	/**
	 * @return the ratio
	 */
	public float getRatio() {
		return ratio;
	}

	public Node(int val, Node n) {
		value = val;
	}
	
	public int get() {
		return value;
	}

	/**
	 * @return the depth
	 */
	public int getDepth() {
		return depth;
	}

	/**
	 * @param depth the depth to set
	 */
	public void setDepth(int depth) {
		this.depth = depth;
	}

	/**
	 * @return the value
	 */
	public int getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(int value) {
		this.value = value;
	}

	/**
	 * @return the weight
	 */
	public int getWeight() {
		return weight;
	}

	/**
	 * @param weight the weight to set
	 */
	public void setWeight(int weight) {
		this.weight = weight;
	}

	/**
	 * @return the bound
	 */
	public float getBound() {
		return bound;
	}

	/**
	 * @param bound the bound to set
	 */
	public void setBound(float bound) {
		this.bound = bound;
	}
	
	public Node getNext() {
		return next;
	}
	
	public void setNext (Node n) {
		next = n;
	}
	
	public String toString() {
//		return "\nNode - Väärtus: " + value + " Kaal: " + weight;
		return "( " + value + ", " + weight + ", " + ratio + " ) ";
	}

	public ArrayList<Boolean> getValikud() {
		return valikud;
	}

	public void setValikud(ArrayList<Boolean> valikud) {
		this.valikud = valikud;
	}

	public Node trimZeros() {
		for (int i = valikud.size() - 1; i >= 0; i++) {
			if (valikud.get(i)) {
//				System.out.print("1");
				break;
			}
			else {
//				System.out.print("0");
				valikud.remove(valikud.size() - 1);
			}
		}
		return this;
	}
	
	

}