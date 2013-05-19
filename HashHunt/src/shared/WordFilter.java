package shared;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class WordFilter {
	
	private static ArrayList<String> excludedWords = new ArrayList<String>(); 
	
	public static void initialize() throws FileNotFoundException, IOException {
		BufferedReader br = new BufferedReader(new FileReader("config\\wordFilter.csv"));
		String line = br.readLine();
		while (line != null) {
			excludedWords.add(line.toLowerCase());
			line = br.readLine();
		}
		br.close();
		System.out.println(excludedWords);
		
	}

	public static boolean checkSuitability(String word) {
		return !excludedWords.contains(word.toLowerCase());
	}

}
