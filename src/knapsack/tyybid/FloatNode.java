package knapsack.tyybid;

public class FloatNode {
	
	private float ratio;
	private int weight;
	private int value;
	private FloatNode next;
	
	public FloatNode(int w, int v) {
		weight = w;
		value = v;
		ratio = ((float) v) / ((float) w);
	}
	
	public FloatNode(int x, FloatNode first) {
		value = x;
		next = first;
	}

	public float get() {
		return ratio;
	}

	public int getWeight() {
		return weight;
	}

	public int getValue() {
		return value;
	}
	
	public String toString() {
		return "\n" + "Kaal: " + weight + " V��rtus: " + value + " Suhe: " + ratio;		
	}

	public FloatNode getNext() {
		return next;
	}

}
