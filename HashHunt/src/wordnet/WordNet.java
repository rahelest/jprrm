package wordnet;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

public class WordNet {

	String wordNetPropertiesPath = "src\\wordnet\\wordnet.properties";
	String wordNetPath;

	public WordNet() {

		Properties properties = new Properties();

		try {
			properties.load(new FileInputStream(wordNetPropertiesPath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		wordNetPath = properties.getProperty("PATH");
	}

	public String getWord(String word) {
		String result = "";

		try {
			
			Runtime runTime = Runtime.getRuntime();

			String command = "cmd /c wn " + word + " -n1 -hypen";
			Process process = runTime.exec(
					"cmd /c cd " + wordNetPath);
			
			process.waitFor();

			process = runTime.exec(command);

			process.waitFor();

			BufferedReader reader = new BufferedReader(new InputStreamReader(
					process.getInputStream()));
			String line = reader.readLine();

			while (line != null) {
				result += line;
				line = reader.readLine();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

	public static void main(String[] args) {
		WordNet wn = new WordNet();
		System.out.println(wn.getWord("dog"));
	}
}
