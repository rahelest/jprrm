package UusPakk;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class JaanuseHargneJaKarbi {
	
	private static NodePriorityQueue PQ;
	private static float maxProfit;	
	private static Node vanem;
	private static Node jargmisega;
	private static Node jargmiseta;
	private static DynamicArray values = new DynamicArray(1);
	private static DynamicArray weights = new DynamicArray(1);
	private static DynamicArray valikud;
	private static DynamicArray valikudJargmisega;
	private static DynamicArray valikudJargmiseta;
	private static NodePriorityQueue items = new NodePriorityQueue();
	private static int sackCapacity;
	private static int itemCount;
	private static NodeDynamicArray bestNodes = new NodeDynamicArray(1);
	
	public static void arvuta(boolean karpega) {
		readInputFileAndFillArrays();
		initialize();
		while (!PQ.isEmpty()) {
			vanem = PQ.dequeueNode();
//System.out.println("------uus-----round----- " + vanem);
			if (vanem.getBound() >= maxProfit || !karpega) {		
				valikud = vanem.getValikud();
//				System.out.println("Valikute maht: " + valikud.lastElement());
				int jargmiseDepth = vanem.getDepth() + 1;
				jargmisega = new Node(jargmiseDepth,vanem.getValue() + values.get(jargmiseDepth),vanem.getWeight() + weights.get(jargmiseDepth));
				valikudJargmisega = valikud.clone();
				valikudJargmisega.add(1);
//				System.out.println();
				jargmisega.setValikud(valikudJargmisega);
				jargmisega.setBound(bound(jargmisega));
				if (jargmisega.getWeight() <= sackCapacity && jargmisega.getValue() >= maxProfit) {
					maxProfit = jargmisega.getValue();
					bestNodes.add(jargmisega);
				}
				if(jargmisega.getBound() > maxProfit || !karpega) {
					PQ.enqueue(jargmisega);
				}
				jargmiseta = new Node(vanem.getDepth() + 1,vanem.getValue(),vanem.getWeight());
				valikudJargmiseta = valikud.clone();
				valikudJargmiseta.add(0);
				jargmiseta.setValikud(valikudJargmiseta);
				jargmiseta.setBound(bound(jargmiseta));
				if(jargmiseta.getBound() > maxProfit || !karpega) {
					PQ.enqueue(jargmiseta);
				}
			}
		}
System.out.println(maxProfit);


		int most = bestNodes.get(bestNodes.getLastElementIndex()).getValue();
		
		System.out.println(bestNodes);
		for (int f = bestNodes.getLastElementIndex(); f >= 0; f--) {
			if (bestNodes.get(f).getValue() < most) break;
			int vaartused = 0;
			int kaalud = 0;
			DynamicArray valikud = bestNodes.get(f).getValikud();
System.out.println(valikud);
			for (int i = 0; i <= valikud.lastElementsIndex; i++) {
				if (valikud.get(i) == 1) {
//System.out.println(values.get(i) + " " + weights.get(i));
					vaartused += values.get(i);
					kaalud += weights.get(i);
				}
			}
			System.out.println(" kokku: " + vaartused + " ja " + kaalud + "\n");
		}
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
//System.out.print("Loetud rida: " + rida);
				String[] temp = rida.split(" ");
				Node n = new Node(0, Integer.parseInt(temp[0]), Integer.parseInt(temp[1]));
				n.setBound(n.getRatio());		
//System.out.println(" " + n.getBound());
				items.enqueue(n);
				rida = br.readLine();
				i++;
			}
			br.close();
			itemCount = i;
//System.out.println(items);
			while (!items.isEmpty()) {
				Node n = items.dequeueNode();
//System.out.println(n);
				values.add(n.getValue());
				weights.add(n.getWeight());
			}
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
System.out.println("Päris esimene bound: " + vanem.getBound());
		PQ.enqueue(vanem);
		PQ.enqueue(vanem);
//System.out.println("Tühi sisendvanem: " + vanem);
//System.out.println("Ja PQ: \t" + PQ);
	}
	
	private static float bound(Node input) {
//		System.out.println(values);
//		System.out.println(weights);
		float result = input.getValue();
		int i = 0;
		int j = 0;
		int selectionWeight = 0;
		if (input.getWeight() >= sackCapacity) {
			return 0;
		} else if (input.getDepth() <= weights.lastElement()) {
			i = input.getDepth() + 1;
			selectionWeight = input.getWeight();
//			System.out.println(result + " " + selectionWeight + " " + i);
			while( i <= itemCount && (selectionWeight + weights.get(i)) <= sackCapacity) {
				selectionWeight = selectionWeight + weights.get(i);
				result = result + values.get(i);
//				System.out.println(result + "\t" + values.get(i) + "\t/" + selectionWeight + "\t" + weights.get(i) + "\t/" + i);
				i++;
			}
//			System.out.println(i + " " + itemCount + " " + selectionWeight + " " + sackCapacity);
//			System.out.print(result + " ");
			j=i;
			if (j <= itemCount) {
				result = result + ((float)(sackCapacity - selectionWeight)) * (((float)values.get(j)) / ((float)weights.get(j)));
			}
//			System.out.println(result + "\n");
		}
		return result;		
	}
}
