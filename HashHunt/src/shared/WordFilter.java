package shared;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

public class WordFilter {
	
	private static ArrayList<String> excludedWords = new ArrayList<String>(); 
	
	public static void initialize() throws FileNotFoundException, IOException {
		Properties props = new Properties();
		props.load(new FileInputStream("config\\wordfilter.properties"));
		String excludedWordsFilePath = props.getProperty("PATH");
		loadExcludedWords(excludedWordsFilePath);
	}

	private static void loadExcludedWords(String excludedWordsFilePath) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(excludedWordsFilePath));
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
