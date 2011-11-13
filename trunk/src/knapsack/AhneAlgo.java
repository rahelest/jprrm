package knapsack;

import java.util.ArrayList;

import ylesanne_3.tyybid.FloatNode;
import ylesanne_3.tyybid.FloatPriorityQueue;

public class AhneAlgo {

	private static int mahutavus;
	private static int kaal = 0;
	private static float koguv��rtus = 0;
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
				koguv��rtus += temp.getValue();
			} else {
				int weightRemaining = mahutavus - kaal;
				float murdv��rtus = (temp.getValue()*(weightRemaining/temp.getWeight()));
				koguv��rtus += murdv��rtus;
				break;
			}
		}
		System.out.println("\n" + kotisisu + "\n");
		return koguv��rtus;
	}
}
