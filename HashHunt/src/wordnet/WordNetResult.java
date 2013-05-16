package wordnet;

import java.util.ArrayList;

public class WordNetResult {
	
	String word;
	
	ArrayList<ArrayList<String>> hypernyms = new ArrayList<ArrayList<String>>();
	
	public WordNetResult(String word) {
		this.word = word;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public ArrayList<ArrayList<String>> getHypernyms() {
		return hypernyms;
	}

	public void addHypernyms(ArrayList<String> hypernyms2) {
		this.hypernyms.add(hypernyms2);
	}
	
	public String toString() {
		String result = word + " (" + hypernyms.size() + "):\n";
		
		for (ArrayList<String> h: hypernyms) {
			result += h + "\n";
		}
		return result;
	}
	

}
