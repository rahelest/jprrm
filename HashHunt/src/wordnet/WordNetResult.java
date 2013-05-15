package wordnet;

import java.util.ArrayList;
import java.util.LinkedList;

public class WordNetResult {
	
	String word;
	
	ArrayList<LinkedList<String>> hypernyms = new ArrayList<LinkedList<String>>();
	
	public WordNetResult(String word) {
		this.word = word;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public ArrayList<LinkedList<String>> getHypernyms() {
		return hypernyms;
	}

	public void addHypernyms(LinkedList<String> hypernyms) {
		this.hypernyms.add(hypernyms);
	}
	

}
