package knapsack;

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

public class TestYlesanne {
	
	/**
	 */
	public static void main(String[] args) throws Exception {
		String sisendFail = "C:\\" + "15.in";
		knapsack.tyybid.FloatPriorityQueue p = new knapsack.tyybid.FloatPriorityQueue();
		
		try {
			BufferedReader br = new BufferedReader(
					new InputStreamReader(new FileInputStream(
								sisendFail)));
			
			String rida = br.readLine();
			int mahutavus = Integer.parseInt(rida);
			
			tyhjaks();
			rida = br.readLine();
			while(rida != null) {				
//				System.out.println("Rida: " + rida);
				String[] temp = rida.split(" ");
//				System.out.print(temp[0] +" "+temp[1]+"\n");
				p.enqueue(Integer.parseInt(temp[1]), Integer.parseInt(temp[0]));
				rida = br.readLine();
			}
			//siin on sisse loetud
			br.close();
//			AhneAlgo ahne = new AhneAlgo(p.clone(), mahutavus);
//			System.out.println(ahne.calculateProbablyBest());
			System.out.println("--------------");
			p.toMyString();
			
			
		} catch (FileNotFoundException e) {
			System.out.println("Faili ei leitud!");
			return;
		} catch (IOException e) {
			System.out.println("Lugemisel juhtus viga: " + e);
			return;
		}
		
//		JA N��D ALGOTAMA:
		
		try {
			BufferedReader br = new BufferedReader(
					new InputStreamReader(new FileInputStream(
								sisendFail)));
			
			String rida = br.readLine();
			int mahutavus = Integer.parseInt(rida);
			
			tyhjaks();
			rida = br.readLine();
			while(rida != null) {				
//				System.out.println("Rida: " + rida);
				String[] temp = rida.split(" ");
//				System.out.print(temp[0] +" "+temp[1]+"\n");
				p.enqueue(Integer.parseInt(temp[1]), Integer.parseInt(temp[0]));
				rida = br.readLine();
			}
			//siin on sisse loetud
			br.close();
//			AhneAlgo ahne = new AhneAlgo(p.clone(), mahutavus);
//			System.out.println(ahne.calculateProbablyBest());
			System.out.println("--------------");
			p.toMyString();
			
			
		} catch (FileNotFoundException e) {
			System.out.println("Faili ei leitud!");
			return;
		} catch (IOException e) {
			System.out.println("Lugemisel juhtus viga: " + e);
			return;
		}
		
	}

	
/*	/**
	
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
	*/
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
