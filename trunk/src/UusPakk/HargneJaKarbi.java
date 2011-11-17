package UusPakk;

/**
 * @author t083851 Jaanus Piip
 * @author t093563 Rahel Rjadnev-Meristo
 *
 */

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class HargneJaKarbi {
	
	private NodePriorityQueue PQ;
	private NodeStack stack;
	private float maxProfit;	
	private Node vanem;
	private Node jargmisega;
	private Node jargmiseta;
	private DynamicArray values;
	private DynamicArray weights;
	private ArrayList<Boolean> valikud;
	private ArrayList<Boolean> valikudJargmisega;
	private ArrayList<Boolean> valikudJargmiseta;
	private NodePriorityQueue items;
	private int sackCapacity;
	private int itemCount;
	private NodeDynamicArray bestNodes;
	private boolean PQga;
	
	public void knapsack(boolean karpega, boolean pqga) {
		initializeDynArrays();
		PQga = pqga;
		readInputFileAndFillArrays();
		initialize();
		while ((PQga && !PQ.isEmpty()) || (!PQga && !stack.isEmpty())) {
			if (PQga) {
				vanem = PQ.dequeueNode();
				
			} else {
				vanem = stack.pop();
			}
			
//System.out.println("------uus-----round----- " + vanem);
			if ((vanem.getBound() >= maxProfit || !karpega) && (vanem.getDepth() < values.lastElementsIndex)) {		
//				System.out.println(PQ.size() + " " + vanem.getDepth() + " " + values.lastElementsIndex);
				valikud = vanem.getValikud();
//				System.out.println("Valikute maht: " + valikud.lastElement());
				int jargmiseDepth = vanem.getDepth() + 1;
				jargmisega = new Node(jargmiseDepth,vanem.getValue() + values.get(jargmiseDepth),vanem.getWeight() + weights.get(jargmiseDepth));
				valikudJargmisega = (ArrayList<Boolean>) valikud.clone();
				valikudJargmisega.add(true);
//				System.out.println();
				jargmisega.setValikud(valikudJargmisega);
				jargmisega.setBound(bound(jargmisega));
				if (jargmisega.getWeight() <= sackCapacity && jargmisega.getValue() >= maxProfit) {
					maxProfit = jargmisega.getValue();
					bestNodes.add(jargmisega);
				}
				if(jargmisega.getBound() > maxProfit || !karpega) {
					if (PQga) {
						PQ.enqueue(jargmisega);
//						System.out.println("GA");
					} else {
						stack.push(jargmisega);
					}
					
				}
				jargmiseta = new Node(jargmiseDepth,vanem.getValue(),vanem.getWeight());
				valikudJargmiseta = (ArrayList<Boolean>) valikud.clone();
				valikudJargmiseta.add(false);
				jargmiseta.setValikud(valikudJargmiseta);
				jargmiseta.setBound(bound(jargmiseta));
				if(jargmiseta.getBound() > maxProfit || !karpega) {
					if (PQga) {
						PQ.enqueue(jargmiseta);
//						System.out.println("TA");
					} else {
						stack.push(jargmiseta);
					}
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
			valikud = bestNodes.get(f).getValikud();
System.out.println(valikud);
			for (int i = 0; i < valikud.size(); i++) {
				if (valikud.get(i).booleanValue()) {
//System.out.println(values.get(i) + " " + weights.get(i));
					vaartused += values.get(i);
					kaalud += weights.get(i);
				}
			}
			System.out.println(" kokku: " + vaartused + " ja " + kaalud + "\n");
		}
	}
	
	private void initializeDynArrays() {
		bestNodes = new NodeDynamicArray(1);
		values = new DynamicArray(1);
		weights = new DynamicArray(1);
		
	}

	private void readInputFileAndFillArrays() {
		items = new NodePriorityQueue();
		String sisendFail = "C:\\" + "100.in";
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

	private void initialize() {
		if (PQga) {
			PQ = new NodePriorityQueue();
		} else {
			stack = new NodeStack();
		}
		maxProfit = 0;
		jargmisega = new Node();
		vanem = new Node();
		vanem.setBound(bound(vanem));
		vanem.setValikud(new ArrayList<Boolean>());
System.out.println("Päris esimene bound: " + vanem.getBound());
		if (PQga) {
			PQ.enqueue(vanem);
			PQ.enqueue(vanem);
		} else {
			stack.push(vanem);
		}
		
//System.out.println("Tühi sisendvanem: " + vanem);
//System.out.println("Ja PQ: \t" + PQ);
	}
	
	private float bound(Node input) {
//		System.out.println(values);
//		System.out.println(weights);
		float result = input.getValue();
		int i = 0;
		int j = 0;
		int selectionWeight = 0;
		if (input.getWeight() >= sackCapacity) {
			return 0;
		} else if (input.getDepth() < weights.lastElement()) {
			i = input.getDepth() + 1;
			selectionWeight = input.getWeight();
//			System.out.println(itemCount + " " + i + " " + i);
			while( i < itemCount && (selectionWeight + weights.get(i)) <= sackCapacity) {
				selectionWeight = selectionWeight + weights.get(i);
				result = result + values.get(i);
//				System.out.println(result + "\t" + values.get(i) + "\t/" + selectionWeight + "\t" + weights.get(i) + "\t/" + i);
				i++;
			}
//			System.out.println(i + " " + itemCount + " " + selectionWeight + " " + sackCapacity);
//			System.out.print(result + " ");
			j=i;
			if (j < itemCount) {
				result = result + ((float)(sackCapacity - selectionWeight)) * (((float)values.get(j)) / ((float)weights.get(j)));
			}
//			System.out.println(result + "\n");
		}
		return result;		
	}
}
