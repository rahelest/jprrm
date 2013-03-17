package shared;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Writer {
	
	FileWriter fstream = null;
	BufferedWriter out = null;
	
	public Writer() {
		
		try {
			fstream = new FileWriter("E:\\flickr.txt");
			out = new BufferedWriter(fstream);
			
		} catch (Exception e) {
			System.err.println("Ei saanud streami avada: " + e.getMessage());
		}
	}
	
	public void writeToFile(String output) {
		
		try {
			out.write(output + "\n");
		} catch (IOException e) {
			System.out.println("Probleem kirjutamisel: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void closeStream() {
		try {
			out.close();
		} catch (IOException e) {
			System.out.println("Ei õnnestunud streami sulgeda: " + e.getMessage());
			e.printStackTrace();
		}
		
	}

}
