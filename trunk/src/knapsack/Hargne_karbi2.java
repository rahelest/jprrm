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
	
	boolean[] ele;
	
	FloatPriorityQueue PQ = new FloatPriorityQueue();
	
	TreeNode vanem = null;
	
	public void tegutse () {
		
		FloatPriorityQueue p = looAsjad(sisendFail);

		AhneAlgo.setKamber(p.toArrayList());
		size = p.getD().len();
		ele = fillElementArray();
		bestValue = 0;

		PQ.enqueue(new TreeNode(0, 0, 0, ele));
		
		while (!PQ.isEmpty()) {
			System.out.println("^^^^^^^^^^^ PQ algus: " + PQ + " ^^^^^^^^^^^");

			vanem = PQ.dequeue();
			
			ele = vanem.getElements();
			System.out.println("___________ Selle tsükli vanem: " + vanem);
			
			int depth = vanem.getDepth() + 1;
			if (AhneAlgo.calculateProbablyBest(ele) < bestValue) {
				break;
			}
			
			if (depth >= ele.length) {
				continue;
			}

			accumulatedValue = vanem.getValue();
			accumulatedWeight = vanem.getWeight();
			
			int i = 0;
			
			ele[depth] = false;
			
			while ( i < 2) {
				if (accumulatedValue > bestValue) {
					bestValue = accumulatedValue;
				}	
			
				if (AhneAlgo.calculateProbablyBest(ele) > bestValue) {
					System.out.print("___________ Lisasin uue elemendi: ");
					PQ.enqueue(new TreeNode(accumulatedValue, accumulatedWeight, depth, ele));
					System.out.println("ZZZZZZZZZZZ PQ nüüd selline: " + PQ);
				}				
				
				if (i > 0) break;
				
				i++;
				TreeNode node;
				if (!p.isEmpty()) {
					node = p.dequeue();
				} else break;
				ele[node.getDepth()] = true;
				accumulatedValue += node.getValue();
				accumulatedWeight += node.getWeight();
			}
			
			System.out.println("PQ lopp: " + PQ);

		}
		
		System.out.println("Vastus: " + vanem);
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
			int mahutavus = Integer.parseInt(rida);
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