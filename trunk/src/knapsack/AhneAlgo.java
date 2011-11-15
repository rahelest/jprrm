package knapsack;

import knapsack.tyybid.TreeNode;

public class AhneAlgo {

	private static int mahutavus;
	private static int kaal = 0;
	private static float koguvaartus = 0;
//	private static ArrayList<TreeNode> kotisisu;
	private static TreeNode[] kamber;
	
	public static void setKambriSisu(TreeNode[] pointer) {
		kamber = pointer;
	}
	
	public static void setMahutavus (int m) {
		mahutavus = m;
	}
	
	
	public static float calculateProbablyBest(boolean[] elements) {
//		System.out.println(Hargne_karbi2.arrayElements(elements));
		for (int i = 0; i < kamber.length; i++) {
			if (elements[i]) {
				TreeNode temp = kamber[i];
				if(kaal + temp.getWeight() <= mahutavus) {
//					kotisisu.add(temp);
					kaal += temp.getWeight();
					koguvaartus += temp.getValue();
				} else {
					int weightRemaining = mahutavus - kaal;
					float murdvaartus = (temp.getValue()*(weightRemaining/temp.getWeight()));
					koguvaartus += murdvaartus;
					break;
				}
			}
		}
//		System.out.println("\n" + kotisisu + "\n");
		return koguvaartus;
	}
}
