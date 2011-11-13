package knapsack.tyybid;

public class FloatNode {
	
	private float ratio;
	private int weight;
	private int value;
	
	public FloatNode(int w, int v) {
		weight = w;
		value = v;
		ratio = ((float) v) / ((float) w);
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

}
