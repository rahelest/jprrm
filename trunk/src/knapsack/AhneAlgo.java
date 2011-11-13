package knapsack;

import java.util.ArrayList;

import knapsack.tyybid.FloatNode;
import knapsack.tyybid.FloatPriorityQueue;

public class AhneAlgo {

	private static int mahutavus;
	private static int kaal = 0;
	private static float koguvaartus = 0;
	private static ArrayList<FloatNode> kotisisu;
//	private FloatPriorityQueue kamber;
//	private float oletus;
	
//	public AhneAlgo(FloatPriorityQueue pointer, int vaartus) {
//		kamber = pointer;
//		mahutavus = vaartus;
//	}
//	
//	public AhneAlgo(FloatPriorityQueue pointer, int vaartus, boolean t) {
//		kamber = pointer;
//		mahutavus = vaartus;
//		oletus =  calculateProbablyBest();
//	}	
//	
//	public static float getOletus() {
//		return oletus;
//	}
	
	public static void setMahutavus (int m) {
		mahutavus = m;
	}
	
	
	public static float calculateProbablyBest(FloatPriorityQueue pointer) {
		FloatPriorityQueue kamber = pointer;
		kotisisu = new ArrayList<FloatNode>();
		while (!kamber.isEmpty()) {
			FloatNode temp = kamber.dequeue();
			if(kaal + temp.getWeight() <= mahutavus) {
				kotisisu.add(temp);
				kaal += temp.getWeight();
				koguvaartus += temp.getValue();
			} else {
				int weightRemaining = mahutavus - kaal;
				float murdvaartus = (temp.getValue()*(weightRemaining/temp.getWeight()));
				koguvaartus += murdvaartus;
				break;
			}
		}
		System.out.println("\n" + kotisisu + "\n");
		return koguvaartus;
	}
}
