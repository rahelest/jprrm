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
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class HargneJaKarbi {
	
	/**
	 * Priority queue, kuhu lähevad töödeldavad Node-d.
	 */
	private NodePriorityQueue PQ;
	/**
	 * Sügavuti otsingu puhul kasutatav stack
	 */
	private NodeStack stack;
	/**
	 * Hetkel kehtiv leitud parim lahend
	 */
	private float maxProfit;
	/**
	 * Hetkel vaadeldav Node, mille lapsväärtusi hakkame analüüsima
	 */
	private Node vanem;
	/**
	 * Node, mis tekib järgmist elementi võtmata
	 */
	private Node jargmisega;
	/**
	 * Node, mis tekib kui võtta järgmine element
	 */
	private Node jargmiseta;
	/**
	 * Sisse loetud väärtuste dünaamiline array
	 */
	private DynamicArray values;
	/**
	 * Sisse loetud kaalude dünaamiline array
	 */
	private DynamicArray weights;
	/**
	 * Hetkel kehtiv nimekiri valitud elementidest
	 */
	private ArrayList<Boolean> valikud;
	/**
	 * Nimekiri, mis tekib järgmist elementi võtmata
	 */
	private ArrayList<Boolean> valikudJargmisega;
	/**
	 * Nimekiri, mis tekib võttes järgmise elemendi
	 */
	private ArrayList<Boolean> valikudJargmiseta;
	/**
	 * Priority queue, kus hoitakse Node all esemeid, väljal bound hoitakse väärtuse/kaalu suhet 
	 */
	private NodePriorityQueue items;
	/**
	 * Lubatud maksimaalne kaal
	 */
	private int sackCapacity;
	/**
	 * Toas eksisteerivate esemete arv
	 */
	private int itemCount;
	/**
	 * Set, mis hoiab võimalikke häid lahendeid
	 */
	private Set<Node> bestNodes;
	/**
	 * Boolean, mis määrab kas kasutatakse parim-enne või sügavutiotsingut
	 */
	private boolean PQga;	
	PrintWriter pw = null;
	
	/**
	 * Klassikaline 0-1 Knapsacki lahendaja võimalusega teha parim-enne, sügavuti ja kärpimiseta otsingut.
	 * @param karpega kas kasutada kärpimist?
	 * @param pqga kas kasutada priorityqueued või stacki?
	 * @param inputFileName path sisendinfot hoidva failini
	 */
	public void knapsack(boolean karpega, boolean pqga, String inputFileName) {
		PQga = pqga;
		initializeDynArrays();		
		readInputFileAndFillArrays(inputFileName);
		String outputFileName = parseOutputFile(inputFileName);
		initialize();
		
		/**
		 * Siinkohal algab reaalne algoritm, muutujad PQ ja karpega määravad algoritmi olemuse.
		 */
		while ((PQga && !PQ.isEmpty()) || (!PQga && !stack.isEmpty())) {
			/**
			 * Sügavuti otsingu puhul pop, muul ajal dequeue
			 */
			if (PQga) {
				vanem = PQ.dequeueNode();
			} else {
				vanem = stack.pop();
			}
			
			/**
			 * Kui hetkel vaadeldav olukord lubab head tulemust ja jätkub veel kaalutavaid elemente, vaatleme vanema lapsi
			 */
			if ((vanem.getBound() >= maxProfit || !karpega) && (vanem.getDepth() < values.lastElementsIndex)) {		
				valikud = vanem.getValikud();
				/**
				 * Mis juhtub kui järgmise pistame kotti?
				 */
				int jargmiseDepth = vanem.getDepth() + 1;
				jargmisega = new Node(jargmiseDepth,vanem.getValue()
						+ values.get(jargmiseDepth),vanem.getWeight() + weights.get(jargmiseDepth));
				valikudJargmisega = (ArrayList<Boolean>) valikud.clone();
				valikudJargmisega.add(true);
				jargmisega.setValikud(valikudJargmisega);
				jargmisega.setBound(bound(jargmisega));
				/**
				 * Kui järgmine element võttes tekib soodne olukord, muudame vastavalt oma parimat leitud väärtust ja esemete kogumikku
				 */
				if (jargmisega.getWeight() <= sackCapacity && jargmisega.getValue() >= maxProfit) {
					maxProfit = jargmisega.getValue();
					bestNodes.add(jargmisega.trimZeros());
				}
				/**
				 * Kui järgmine element lubab head väärtust, lisame ta priorityqueuesse
				 */
				if(jargmisega.getBound() > maxProfit || !karpega) {
					if (PQga) {
						PQ.enqueue(jargmisega);
					} else {
						stack.push(jargmisega);
					}
					
				}
				/**
				 * Mis juhtub kui järgmist eset ei pistaks kotti?
				 */
				jargmiseta = new Node(jargmiseDepth,vanem.getValue(),vanem.getWeight());
				valikudJargmiseta = (ArrayList<Boolean>) valikud.clone();
				valikudJargmiseta.add(false);
				jargmiseta.setValikud(valikudJargmiseta);
				jargmiseta.setBound(bound(jargmiseta));
				/**
				 * Kui järgmise mitte võtmine lubab soodsamat olukorda, pistame ta priorityqueuesse
				 */
				if(jargmiseta.getBound() > maxProfit || !karpega) {
					if (PQga) {
						PQ.enqueue(jargmiseta);
					} else {
						stack.push(jargmiseta);
					}
				}
			}
		}
		System.out.println("Maxprofit: " + maxProfit);
		/**
		 * Käime läbi parima valikupuu ning kirjutame vastavad kaalud-väärtused stringidesse
		 */		
		int most = 0;
		Iterator<Node> bestNodesIterator = bestNodes.iterator();
		while (bestNodesIterator.hasNext()) {
			Node n = (Node) bestNodesIterator.next();
			if (n.getValue() > most) most = n.getValue();
		}
		bestNodesIterator = bestNodes.iterator();
		initializeWriting(outputFileName);
		while (bestNodesIterator.hasNext()) {
			Node n = (Node) bestNodesIterator.next();
			if (n.getValue() >= most) {
				int vaartused = 0;
				int kaalud = 0;
				valikud = n.getValikud();
				for (int i = 0; i < valikud.size(); i++) {
					if (valikud.get(i).booleanValue()) {
						vaartused += values.get(i);
						kaalud += weights.get(i);
					}
				}
				pw.println(vaartused + " " + kaalud);
				break;
			}
		}
		
		writeToFile();
	}
	
	/**
	 * Kirjutab algoritmi töö tulemuse faili.
	 */
	private void writeToFile() {
		for (int i = 0; i < valikud.size(); i++) {
			if (valikud.get(i).booleanValue()) {
				pw.println(values.get(i) + " " + weights.get(i));
			}
		}
		pw.flush();		// puhver t�hjaks
		pw.close();		// fail kinni
			
	}
	
	/**
	 * Initsialiseerib sisendinfo hoidmiseks vajalikud dünaamilised massiivid.
	 */
	private void initializeDynArrays() {
		bestNodes = new HashSet<Node>();
		values = new DynamicArray(4);
		weights = new DynamicArray(4);
		
	}

	/**
	 * Loeb sisendfailist koti mahutavuse ja kõik elemendid,
	 * seejärel lisab ta elemendid ühte PQ-sse väärtuse/kaalu suhte järgi (hoiame seda väljal bound).
	 * @param inputFileName fail, millest sisendinfo loetakse
	 */
	private void readInputFileAndFillArrays(String inputFileName) {
		items = new NodePriorityQueue();
		String sisendFail = inputFileName;
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
			/**
			 * Organiseerime toas leiduvad esemed väärtuse/kaalu suhte järjekorda
			 */
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

	/**
	 * Initsialiseerib algoritmi tööks vajalikud muutujad.
	 */
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
		if (PQga) {
			PQ.enqueue(vanem);
			PQ.enqueue(vanem);
		} else {
			stack.push(vanem);
		}
	}
	
	/**
	 * Arvutab antud sisendpuuga parima võimaliku murdosalise lahenduse.
	 * @param input Node, mis hoiab hinnatavat valikupuud
	 * @return float väärtus, mis hoiab murdosalist parimat võimalikku tulemust
	 */
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
	
	/**
	 * Loob väljundi kirjutamiseks vajaliku printwriteri.
	 * @param outputFileName path väljundfailini
	 */
	private void initializeWriting(String outputFileName) {
		
		String sisendfail  = outputFileName;
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
	
	/**
	 * Loeb sisendfaili nime ja tõlgib selle väljundfaili nimeks.
	 * @param inputFileName sisendfaili nimi
	 * @return path väljundfailini ja selle nimi
	 */
	private String parseOutputFile(String inputFileName) {
		String outputPath = "";		
		String[] temp = inputFileName.split("/");
		String filename = temp[temp.length - 1];
		for (int i = 0; i < temp.length - 1; i++) {
			outputPath += temp[i] + "/";
		}
		temp = filename.split(".in");
		String outputNumber = temp[0];
		return outputPath + outputNumber + ".out";		
	}
}
