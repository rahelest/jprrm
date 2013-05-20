package wordnet;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import shared.SystemCaller;

public class WordNet {

	String wordNetPropertiesPath = "config\\wordnet.properties";
	String wordNetPath = null;

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

	public void getWord(String word) {
		SystemCaller sysCall = new SystemCaller();
		sysCall.execute(this, wordNetPath + "wn.exe " + word + " -hypen");
	}

	public static void main(String[] args) {
		WordNet wn = new WordNet();
		wn.getWord("dog");
	}

	WordNetResult wnr = null;
	
	public WordNetResult getResult() {
		while (wnr == null) {
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return wnr;
	}

	public void setResult(WordNetResult parsedHypernymTree) {
		this.wnr = parsedHypernymTree;
	}
}
