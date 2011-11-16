package UusPakk;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import knapsack.baastyybid.DynamicArray;
import knapsack.baastyybid.Node;
import knapsack.baastyybid.NodePriorityQueue;

public class JaanuseHargneJaKarbi {
	
	private static NodePriorityQueue PQ;
	private static float maxProfit;	
	private static Node vanem;
	private static Node jargmisega;
	private static Node jargmiseta;
	private static DynamicArray values = new DynamicArray(1);
	private static DynamicArray weights = new DynamicArray(1);
	private static int sackCapacity;
	private static int itemCount;
	
	
	public static void main(String[] args) {
		readInputFileAndFillArrays();
		initialize();
		System.out.println(PQ);
		while (!PQ.isEmpty()) {
			vanem = PQ.dequeueNode();
//			System.out.println("uus round");
//			System.out.println(vanem);
//			System.out.println(vanem.getBound() + " lol " + maxProfit);
			if (vanem.getBound() > maxProfit) {
				jargmisega = new Node();
				jargmisega.setDepth(vanem.getDepth() + 1);
				jargmisega.setWeight(vanem.getWeight() + weights.get(jargmisega.getDepth()));
				jargmisega.setValue(vanem.getValue() + values.get(jargmisega.getDepth()));
//				System.out.println("sygavus: " + jargmisega.getDepth());
				jargmisega.setBound(bound(jargmisega));
//				System.out.println("jargmisega kaal: " + jargmisega.getWeight() + " mahutavus: " + sackCapacity + " jargmisega vaartus: " + jargmisega.getValue() + " maxprofit: " + maxProfit);
				if (jargmisega.getWeight() <= sackCapacity && jargmisega.getValue() >= maxProfit) {
					maxProfit = jargmisega.getValue();
				}				
				if(bound(jargmisega) > maxProfit) {
					PQ.enqueue(jargmisega);
//					System.out.println("lisan ühe");
				}
				jargmiseta = new Node(vanem.getDepth() + 1,vanem.getValue(),vanem.getWeight());
				jargmiseta.setBound(bound(jargmiseta));
				if (jargmiseta.getBound() > maxProfit) {
					PQ.enqueue(jargmiseta);
//					System.out.println("lisan järgmise");
				}
			}
		}
		System.out.println(maxProfit);
	}
	
	private static void readInputFileAndFillArrays() {
		String sisendFail = "C:\\" + "15.in";
		int i = 0;
		try {
			BufferedReader br = new BufferedReader(
					new InputStreamReader(new FileInputStream(
								sisendFail)));
			
			String rida = br.readLine();
			sackCapacity = Integer.parseInt(rida);
			rida = br.readLine();
			while(rida != null) {				
				String[] temp = rida.split(" ");
				values.add(Integer.parseInt(temp[0]));
				weights.add(Integer.parseInt(temp[1]));				
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
		jargmisega = new Node();
		vanem = new Node();
		vanem.setBound(bound(vanem));
		PQ.enqueue(vanem);
		PQ.enqueue(vanem);
	}
	
	private static float bound(Node input) {
		float result = 0;
		int i = 0;
		int j = 0;
		int selectionWeight = 0;
		if (input.getWeight() >= sackCapacity) {
//			System.out.println("boundresult: " + result);
			return 0;
		} else {
			result = input.getValue();
			i = input.getDepth() + 1;			
			selectionWeight = input.getWeight();
			System.out.println(i);
			//weights.get(i) võetakse liiga väike
			while( i <= itemCount && selectionWeight + weights.get(i) <= sackCapacity) {
				selectionWeight = selectionWeight + weights.get(i);
				result = result + values.get(i);
				i++;
			}
			j=i;
			if (j <= itemCount) {
				result = result + (sackCapacity - selectionWeight) * (values.get(j) / weights.get(j));
			}
		}
//		System.out.println("boundresult: " + result);
		return result;		
	}
	

}
