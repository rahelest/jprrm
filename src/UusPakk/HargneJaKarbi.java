package UusPakk;

/**
 * @author t083851 Jaanus Piip
 * @author t093563 Rahel Rjadnev-Meristo
 *
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

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
	private Set<Node> bestNodes;
	private boolean PQga;
	
	PrintWriter pw = null;
	
	public void knapsack(boolean karpega, boolean pqga) {
		initializeDynArrays();
		PQga = pqga;
		readInputFileAndFillArrays();
		initialize();
		while ((PQga && !PQ.isEmpty()) || (!PQga && !stack.isEmpty())) {
			if (PQga) {
				vanem = PQ.dequeueNode();
//				System.out.println(PQ.size());
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
					bestNodes.add(jargmisega.trimZeros());
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
		int most = 0;
		Iterator bestNodesIterator = bestNodes.iterator();
		while (bestNodesIterator.hasNext()) {
			Node n = (Node) bestNodesIterator.next();
			if (n.getValue() > most) most = n.getValue();
		}
		System.out.println("Most: " + most);
		bestNodesIterator = bestNodes.iterator();
		initializeWriting();
		while (bestNodesIterator.hasNext()) {
			Node n = (Node) bestNodesIterator.next();
			if (n.getValue() >= most) {
				int vaartused = 0;
				int kaalud = 0;
				valikud = n.getValikud();
				System.out.println(valikud);
				for (int i = 0; i < valikud.size(); i++) {
					if (valikud.get(i).booleanValue()) {
						vaartused += values.get(i);
						kaalud += weights.get(i);
					}
				}
				System.out.println(" kokku: " + vaartused + " ja " + kaalud + "\n");
				pw.println(vaartused + " " + kaalud);
				break;
			}
		}
		
		writeToFile(valikud);
	}
	
	private void writeToFile(ArrayList<Boolean> valikud2) {
		for (int i = 0; i < valikud.size(); i++) {
			if (valikud.get(i).booleanValue()) {
				pw.println(values.get(i) + " " + weights.get(i));
			}
		}
		pw.flush();		// puhver t�hjaks
		pw.close();		// fail kinni
			
	}
//			}
//		}
//
//		for (int f = bestNodes.bestNodes.size() - 1); f >= 0; f--) {
//			if (bestNodes.get(f).getValue() < most) break;
//			int vaartused = 0;
//			int kaalud = 0;
//			valikud = bestNodes.get(f).getValikud();
//System.out.println(valikud);
//			for (int i = 0; i < valikud.size(); i++) {
//				if (valikud.get(i).booleanValue()) {
////System.out.println(values.get(i) + " " + weights.get(i));
//					vaartused += values.get(i);
//					kaalud += weights.get(i);
//				}
//			}
		
	
	private void initializeDynArrays() {
		bestNodes = new HashSet<Node>();
		values = new DynamicArray(4);
		weights = new DynamicArray(4);
		
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
				String[] temp = rida.split(" ");
				Node n = new Node(0, Integer.parseInt(temp[0]), Integer.parseInt(temp[1]));
				n.setBound(n.getRatio());		
				items.enqueue(n);
				rida = br.readLine();
				i++;
			}
			br.close();
			itemCount = i;
			while (!items.isEmpty()) {
				Node n = items.dequeueNode();

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
	}
	
	private float bound(Node input) {
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
				i++;
			}
			j=i;
			if (j < itemCount) {
				result = result + ((float)(sackCapacity - selectionWeight)) * (((float)values.get(j)) / ((float)weights.get(j)));
			}
		}
		return result;		
	}
	
	private void initializeWriting() {
		
		String sisendfail  = "D:\\100.out";
		try {
			pw = new PrintWriter(
						new BufferedWriter(
								new OutputStreamWriter(
										new FileOutputStream(
											sisendfail, false))));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return;
		}		
	}
}
