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

import knapsack.tyybid.FloatNode;
import knapsack.tyybid.FloatPriorityQueue;

public class Hargne_karbi2 {
	
	public static void main(String[] args) throws Exception {
		
		String sisendFail = "C:\\" + "15.in";
		String valjundFail = "C:\\" + "15.out";
		
		if (args.length == 1) {
			System.out.println("�ks parameeter on puudu! Kasutan default testv��rtusi...");
		} else if (args.length == 2) {
			sisendFail = args[0];
			valjundFail = args[1];
		}
		
		FloatPriorityQueue p = new FloatPriorityQueue();
		
		try {
			BufferedReader br = new BufferedReader(
					new InputStreamReader(new FileInputStream(
								sisendFail)));
			
			String rida = br.readLine();
			int mahutavus = Integer.parseInt(rida);
			rida = br.readLine();
			AhneAlgo.setMahutavus(mahutavus);
			
			tyhjaks();
			
			while(rida != null) {
				String[] temp = rida.split(" ");
				p.enqueue(Integer.parseInt(temp[1]), Integer.parseInt(temp[0]));
				rida = br.readLine();
			}
			br.close();
			
			float bestValue = 0;
			int accumulatedValue = 0;
			int accumulatedWeight = 0;
			
			FloatPriorityQueue PQ = new FloatPriorityQueue();
			//selleks hetkeks on esialgne pq populeeritud
			PQ.enqueue(0, 0);
			bestValue = AhneAlgo.calculateProbablyBest(p.clone());
			System.out.println("Esialgne BEST hinnang: " + bestValue);
			
			FloatPriorityQueue childWith = p.clone();
			FloatPriorityQueue childWithout = p.clone();
			childWithout.dequeue();

			while (!PQ.isEmpty()) {
				
				/*
				 * Dequeueb OQ-st node, tekitab sellele lapsed, anal��sib neid.
				 * 
				 * TODO: kuidas leida pointer?
				 * 
				 * TODO: kaalupiirang?
				 * 
				 */
				
				FloatNode vanem = PQ.dequeue();
				if (AhneAlgo.calculateProbablyBest(pointer) < bestValue) {
					break;
				}
				
//				float withValue = AhneAlgo.calculateProbablyBest(childWith.clone());
//				float withoutValue = AhneAlgo.calculateProbablyBest(childWithout.clone());
//				
//				
//				if(withValue > bestValue) {
//					bestValue = withValue;
//				} else if (withoutValue > bestValue) {
//					bestValue = withoutValue;
//				}
				accumulatedValue = vanem.getValue();
				accumulatedWeight = vanem.getWeight();
				
				int i = 0;
				
//				/**
//				 * v�tame m�lemast �he maha, et simuleerida j�rgmise v��rtuse jaoks sellega ja selleta olukorda
//				 */
//				childWith.dequeue();
//				childWithout.dequeue();
				
				
				while ( i < 2) {
					if (accumulatedValue > bestValue ) {
						bestValue = accumulatedValue;
					}	
				
					if (AhneAlgo.calculateProbablyBest(p.clone()) > bestValue) {
						PQ.enqueue(accumulatedWeight, accumulatedValue);
					}				
					
					i++;
					FloatNode node = p.dequeue();
					accumulatedValue += node.getValue();
					accumulatedWeight += node.getWeight();
				}
				
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("Faili ei leitud!");
			return;
		} catch (IOException e) {
			System.out.println("Lugemisel juhtus viga: " + e);
			return;
		}
		
	}

	

	
	private static void writeToFile(float f, String which) {
		
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
	
	private static void tyhjaks() {
		
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

}
