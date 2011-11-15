package knapsack;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import knapsack.tyybid.FloatPriorityQueue;
import knapsack.tyybid.TreeNode;

public class Hargne_karbi2 {
	
	String sisendFail = "C:\\" + "15.in";
	String valjundFail = "C:\\" + "15.out";
	
	float bestValue = 0;
	int accumulatedValue = 0;
	int accumulatedWeight = 0;
	int size = 0;
	int mahutavus;
	
	boolean[] currentlySelectedBooleans;
	
	FloatPriorityQueue PQ = new FloatPriorityQueue();
	
	TreeNode vanem = null;
	
	public void tegutse () {
		
		FloatPriorityQueue nodiNimekiri = looAsjad(sisendFail);
//		FloatPriorityQueue nodiNimekiri2 = nodiNimekiri.clone();
		TreeNode[] nodiNimekiriStaatiline = new TreeNode[nodiNimekiri.getD().len()];
		
		int i = 0;
		while (!nodiNimekiri.isEmpty()) {
			nodiNimekiriStaatiline[i++] = nodiNimekiri.dequeue();
		}
		
		AhneAlgo.setKambriSisu(nodiNimekiriStaatiline);
		size = nodiNimekiriStaatiline.length;
		currentlySelectedBooleans = fillElementArray();
		bestValue = 0;

		PQ.enqueue(new TreeNode(0, 0, 1, currentlySelectedBooleans));
		
		while (!PQ.isEmpty()) {
			System.out.println("^^^^^^^^^^^ PQ algus: " + PQ + " ^^^^^^^^^^^");

			vanem = PQ.dequeue();
			
			currentlySelectedBooleans = vanem.getElementBooleans();
			System.out.println("___________ Selle tsükli vanem: " + vanem);
			
			int depth = vanem.getDepth() + 1;
			
			if (AhneAlgo.calculateProbablyBest(currentlySelectedBooleans) < bestValue) {
				break;
			}
			
			if (depth >= currentlySelectedBooleans.length) {
				continue;
			}

			accumulatedValue = vanem.getValue();
			accumulatedWeight = vanem.getWeight();
			
			if(accumulatedWeight > mahutavus) {
				makeFalses(vanem);
				continue;
			}
						
			
			i = 0;
			while (i < 2) {
				TreeNode node = nodiNimekiriStaatiline[depth + 1];
				if(i == 0) {
					currentlySelectedBooleans[depth + 1] = false;
				} else {
					currentlySelectedBooleans[depth + 1] = true;
					accumulatedValue += node.getValue();
					accumulatedWeight += node.getWeight();
				}
				
				/*if (accumulatedValue > bestValue) {
					bestValue = accumulatedValue;
				}*/			
				
				if (AhneAlgo.calculateProbablyBest(currentlySelectedBooleans) > bestValue) {
					TreeNode n = new TreeNode(accumulatedValue, 1, depth, currentlySelectedBooleans);
					System.out.println("Lisan: " + n);
					PQ.enqueue(n);
					System.out.println("ZZZZZZZZZZZ PQ nüüd selline: " + PQ);					
				}				
				
				if(accumulatedValue > bestValue) {
					bestValue = accumulatedValue;
				}
				
				i++;
				/*
				TreeNode node = nodiNimekiriStaatiline[depth + 1];
				System.out.println("\nELEMENDID: " + arrayElements(nodiNimekiriStaatiline));
				
				if (AhneAlgo.calculateProbablyBest(currentlySelectedBooleans) > bestValue) {
//					System.out.println("\n___________ Lisasin uue elemendi: " + (accumulatedValue));
					TreeNode n = new TreeNode(accumulatedValue, 1, depth, currentlySelectedBooleans);
					System.out.println("Lisan: " + n);
					PQ.enqueue(n);
					System.out.println("ZZZZZZZZZZZ PQ nüüd selline: " + PQ);
				}				
				
				if (i > 0) break;
				
				i++;
				
				
				System.out.println("NODE: " + node);
				try {
					accumulatedValue += node.getValue();
					accumulatedWeight += node.getWeight();
				} catch (NullPointerException e) {
					System.out.println("PQ : " + PQ);
					System.out.println("Depth: " + depth);
					System.out.println("ELEMENDID: " + arrayElements(nodiNimekiriStaatiline));
					System.exit(-1);
				}*/
			}
			
			System.out.println("PQ lopp: " + PQ);

		}
		
		System.out.println("Vastus: " + vanem + vanem);
	}

	private void makeFalses(TreeNode vanem) {
		boolean[] uus = currentlySelectedBooleans;
		System.out.println(vanem.getDepth() + " ja " + currentlySelectedBooleans.length);
		for (int i = vanem.getDepth(); i < currentlySelectedBooleans.length; i++) {
			uus[i] = false;
			System.out.println("\nuus-i falsified");
		}
		vanem.setElements(uus);
		
	}

	private String arrayElements(TreeNode[] nodiNimekiriStaatiline) {

		String result = nodiNimekiriStaatiline[0] + "";
		for (int i = 1; i < nodiNimekiriStaatiline.length; i++) {
			result += ", " + nodiNimekiriStaatiline[i];
		}
		return "[" + result + "]";
	}

	private boolean[] fillElementArray() {
		boolean[] ele = new boolean[size];
		for (int i = size - 1; i >= 0; i--) {
			ele[i] = true;
		}
		return ele;
	}

	private void failinimed(String[] args) {
		if (args.length == 1) {
			System.out.println("Üks parameeter on puudu! Kasutan default testväärtusi...");
		} else if (args.length == 2) {
			sisendFail = args[0];
			valjundFail = args[1];
		}
	}

	public static String arrayElements(boolean[] ele) {
		String kirjuta;
		
		if (ele[0]) kirjuta = "T";
		else if (!ele[0]) kirjuta = "F";
		else kirjuta = ele[0] + "";
		String result = kirjuta + "";
		
		for (int i = 1; i < ele.length; i++) {
			if (ele[i]) kirjuta = "T";
			else if (!ele[i]) kirjuta = "F";
			else kirjuta = ele[i] + "";
			result += ", " + kirjuta;
		}
		return "[" + result + "]";
	}

	private  FloatPriorityQueue looAsjad(String sisendFail) {
		FloatPriorityQueue p = new FloatPriorityQueue();
		
		try {
			BufferedReader br = new BufferedReader(
					new InputStreamReader(new FileInputStream(
								sisendFail)));
			
			String rida = br.readLine();
			mahutavus = Integer.parseInt(rida);
			rida = br.readLine();
			AhneAlgo.setMahutavus(mahutavus);
			
//			tyhjaks();
			
			while(rida != null) {
				String[] temp = rida.split(" ");
				p.enqueue(Integer.parseInt(temp[1]), Integer.parseInt(temp[0]));
				rida = br.readLine();
			}
			br.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("Faili ei leitud!");
			System.exit(-1);
		} catch (IOException e) {
			System.out.println("Lugemisel juhtus viga: " + e);
			System.exit(-1);
		}
		return p;
	}

	private  void writeToFile(float f, String which) {
		
		String sisendfail;
			sisendfail = "C:\\Users\\rahrja\\Documents\\" + "algofail2.pri";
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(
						new BufferedWriter(
								new OutputStreamWriter(
										new FileOutputStream(
											sisendfail, true))));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return;
		}
		pw.println(f);
//		System.out.println("KIRJUTASIN " + which + " selle " + what);
		
		
		pw.flush();		// puhver t�hjaks
		pw.close();		// fail kinni
	}
	
	private  void tyhjaks() {
		
		String sisendfail = "C:\\Users\\rahrja\\Documents\\" + "algofail2.mag";
		
		PrintWriter pw = null;
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
		pw.print("");
		
		
		pw.flush();		// puhver t�hjaks
		pw.close();		// fail kinni
		
		sisendfail = "C:\\Users\\rahrja\\Documents\\" + "algofail2.pri";
		
		PrintWriter pw2 = null;
		
		try {
			pw2 = new PrintWriter(
						new BufferedWriter(
								new OutputStreamWriter(
										new FileOutputStream(
											sisendfail, false))));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return;
		}
		pw2.print("");
		
		
		pw2.flush();		// puhver t�hjaks
		pw2.close();		// fail kinni
		
	}

	public static void main(String[] args) {
		Hargne_karbi2 h = new Hargne_karbi2();
		h.failinimed(args);
		h.tegutse();
		
	}
}