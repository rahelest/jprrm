package UusPakk;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class JaanuseHargneJaKarbi {
	
	private static NodePriorityQueue PQ;
	private static float maxProfit;	
	private static Node vanem;
	private static Node jargmisega;
	private static Node jargmiseta;
	private static DynamicArray values = new DynamicArray(1);
	private static DynamicArray weights = new DynamicArray(1);
	private static NodePriorityQueue items = new NodePriorityQueue();
	private static int sackCapacity;
	private static int itemCount;
	
	
	public static void main(String[] args) {
		readInputFileAndFillArrays();
		initialize();
		while (!PQ.isEmpty()) {
			vanem = PQ.dequeueNode();
System.out.println("------uus-----round----- " + vanem);
//			System.out.println(vanem);
//			System.out.println(vanem.getBound() + " lol " + maxProfit);
			if (vanem.getBound() > maxProfit) {
				jargmisega = new Node();
				jargmisega.setDepth(vanem.getDepth() + 1);
				jargmisega.setWeight(vanem.getWeight() + weights.get(jargmisega.getDepth()));
				jargmisega.setValue(vanem.getValue() + values.get(jargmisega.getDepth()));
//				System.out.println("sygavus: " + jargmisega.getDepth());
System.out.print("Jargmisega bound: ");
				jargmisega.setBound(bound(jargmisega));
//				System.out.println("jargmisega kaal: " + jargmisega.getWeight() + " mahutavus: " + sackCapacity + " jargmisega vaartus: " + jargmisega.getValue() + " maxprofit: " + maxProfit);
				if (jargmisega.getWeight() <= sackCapacity && jargmisega.getValue() >= maxProfit) {
					maxProfit = jargmisega.getValue();
				}				
				if(jargmisega.getBound() > maxProfit) {
					PQ.enqueue(jargmisega);
//					System.out.println("lisan ühe");
				}
				jargmiseta = new Node(vanem.getDepth() + 1,vanem.getValue(),vanem.getWeight());
System.out.print("Jargmiseta bound: ");
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
System.out.println("Loetud rida: " + rida);
				String[] temp = rida.split(" ");
				Node n = new Node(0, Integer.parseInt(temp[0]), Integer.parseInt(temp[1]));
				n.setBound(n.getRatio());		
				items.enqueue(n);
				rida = br.readLine();
				i++;
			}
			br.close();
			itemCount = i;
System.out.println("Itemcount: " + itemCount);
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

	private static void initialize() {
		PQ = new NodePriorityQueue();
		maxProfit = 0;
		jargmisega = new Node();
		vanem = new Node();
		vanem.setBound(bound(vanem));
System.out.println("Päris esimene bound: " + vanem.getBound());
		PQ.enqueue(vanem);
		PQ.enqueue(vanem);
System.out.println("Tühi sisendvanem: " + vanem);
System.out.println("Ja PQ: \t" + PQ);
	}
	
	private static float bound(Node input) {
		float result = input.getValue();
		int i = 0;
		int j = 0;
		int selectionWeight = 0;
		if (input.getWeight() >= sackCapacity) {
			return 0;
		} else if (input.getDepth() <= weights.lastElement()) {
			i = input.getDepth() + 1;			
			selectionWeight = input.getWeight();
System.out.print("IIII: " + i);
			while( i <= itemCount && selectionWeight + weights.get(i) <= sackCapacity) {
				selectionWeight = selectionWeight + weights.get(i);
				result = result + values.get(i);
				i++;
			}
			j=i;
			if (j <= itemCount) {
				result = result + (sackCapacity - selectionWeight) * (values.get(j) / weights.get(j));
			}
System.out.print(" - " + i);
		} else {
System.out.print("Elemendid otsas");
		}
System.out.println(", boundresult: " + result);
		return result;		
	}
	

}
