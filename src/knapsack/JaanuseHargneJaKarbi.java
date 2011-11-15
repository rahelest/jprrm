package knapsack;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import knapsack.baastyybid.Node;
import knapsack.baastyybid.NodePriorityQueue;

public class JaanuseHargneJaKarbi {
	
	private static NodePriorityQueue PQ;
	private static float maxProfit;
	private static Node u;
	private static Node v;
	private static int[] values;
	private static int[] weights;
	private static int sackCapacity;
	private static int itemCount;
	
	
	public static void main(String[] args) {
		readInputFileAndInitializeArrays();
		initialize();
		while (!PQ.isEmpty()) {
			v = PQ.dequeue();
			if (v.getBound() > maxProfit) {
				u.setDepth(v.getDepth() + 1);
				u.setWeight(v.getWeight() + weights[u.getDepth()]);
				u.setValue(v.getValue() + values[u.getDepth()]);
				if (u.getWeight() <= sackCapacity && u.getValue() > maxProfit) {
					maxProfit = u.getValue();
				}
				u.setBound(bound(u));
				if(bound(u) > maxProfit) {
					PQ.enqueue(u);
				}
				u.setWeight(v.getWeight());
				u.setValue(v.getValue());
				u.setBound(bound(u));
				if (u.getBound() > maxProfit) {
					PQ.enqueue(u);
				}
			}
		}
		
	}
	
	private static void readInputFileAndInitializeArrays() {
		String sisendFail = "C:\\" + "15.in";
		int i = 0;
		try {
			BufferedReader br = new BufferedReader(
					new InputStreamReader(new FileInputStream(
								sisendFail)));
			
			String rida = br.readLine();
			int mahutavus = Integer.parseInt(rida);
			rida = br.readLine();
			while(rida != null) {				
				String[] temp = rida.split(" ");
				values[i] = Integer.parseInt(temp[0]);
				weights[i] = Integer.parseInt(temp[1]);				
				rida = br.readLine();
				i++;
			}
			br.close();
			itemCount = i;
		} catch (FileNotFoundException e) {
			System.out.println("Sisendfaili ei leitud!");
			return;
		} catch (IOException e) {
			System.out.println("Lugemisel juhtus üldine I/O viga!");
			return;
		}
		
	}

	private static void initialize() {
		PQ = new NodePriorityQueue();
		maxProfit = 0;
		u = new Node();
		v = new Node();
		v.setDepth(0);
		v.setValue(0);
		v.setWeight(0);
		v.setBound(bound(v));
		PQ.enqueue(v);
	}
	
	private static float bound(Node input) {
		float result = 0;
		int i = 0;
		int j = 0;
		int selectionWeight = 0;
		
		if (input.getWeight() >= sackCapacity) {
			return 0;
		} else {
			result = input.getValue();
			i = input.getDepth() + 1;			
			selectionWeight = input.getWeight();
			while( i <= itemCount && selectionWeight + weights[i] <= sackCapacity) {
				selectionWeight = selectionWeight + weights[i];
				result = result + values[i];
				i++;
			}
			j=i;
			if (j <= itemCount) {
				result = result + (sackCapacity - selectionWeight) * (values[j] / weights[j]);
			}
		}
		
		return result;		
	}
	

}
