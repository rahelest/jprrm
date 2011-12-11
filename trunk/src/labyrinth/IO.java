package labyrinth;

import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class IO {
	
	/**
	 * Muutuja labürindi sissepääsu meelde jätmiseks.
	 */
	Dimension entrance = new Dimension(0,0);
	
	/**
	 * Muutuja labürindi väljapääsu meelde jätmiseks.
	 */
	Dimension exit = new Dimension(0,0);
	
	/**
	 * Faili nimi, millest hakatakse labürinti lugema
	 * ja kuhu tulemus kirjutatakse.
	 */
	String filename;
	
	/**
	 * Main meetod.
	 * @param args Faili nimi.
	 */
	public static void main(String[] args) {
		new IO(args);
	}
	
	/**
	 * Põhimeetod.
	 * @param args 
	 */
	public IO (String[] args) {
		filename = args[0];
		char[][] maze = readInput(filename);
		Maze m = new Maze(entrance, exit);
//		m.printOut(maze);
		maze = m.solve(maze);
		m.printOut(maze);
		writeOutput(maze);		
	}
	
	/**
	 * Faili info sisselugeja.
	 * @param string Faili nimi, kust loetakse.
	 * @return Labürint, mida töötlema hakatakse.
	 */
	private char[][] readInput(String string) {
		BufferedReader br;
		char [][] maze = null;
		int n = 0;
		try {
			br = new BufferedReader(
						new InputStreamReader(new FileInputStream("in\\" + 
									string + ".in")));
			
			/**
			 * Esimene rida näitab ära labürindi suuruse,
			 * see loetakse sisse eraldi. Vastavalt sellele
			 * luuakse ka 2n+1 dimensioonidega char maatriks.
			 */
			n = Integer.parseInt(br.readLine());
			maze = new char[2 * n + 1][2 * n + 1];

			String line = br.readLine();
			int f = 0;
			while (line != null) {
				for (int i = 0; i < maze.length; i++) {
					maze[f][i] = line.charAt(i);

					/**
					 * Kontrollib, kas loetud täht on B või F,
					 * sel juhul kirjutatakse koordinaadid 
					 * vastavalt entrance või exit muutujasse.
					 */
					if (line.charAt(i) == 'B') {
						entrance = new Dimension(f, i);
					} else if (line.charAt(i) == 'F') {
						exit = new Dimension(f, i);
					}
				}

				f++;
				line = br.readLine();
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("Faili ei leitud! Kontrolli sisendparameetrit.");
			e.printStackTrace();
			System.exit(-1);
		} catch (NumberFormatException e) {
			System.out.println("Esimene rida failis ei ole number!");
			e.printStackTrace();
			System.exit(-1);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return maze;
	}

	/**
	 * Kirjutab labürindi muutuja faili.
	 * @param maze Uus labürint.
	 */
	private void writeOutput(char[][] maze) {
		FileWriter outputStream;
		BufferedWriter out;
		try {
			outputStream = new FileWriter("out\\" +
					filename + ".out");
			out = new BufferedWriter(outputStream);
			for (char[] row : maze) {
				out.write(row);
				out.write("\n");
			}			
			out.close();
		} catch (IOException e) {
			System.out.println("Programm läks katki, kahju küll.");
			e.printStackTrace();
		}		  
		
	}



}
