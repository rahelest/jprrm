package wordnet;

import java.util.ArrayList;

public class WordNetResult {
	
	String word;
	ArrayList<Sense> senses = new ArrayList<Sense>();

	
	public WordNetResult(String word) {
		this.word = word;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}
	
	public ArrayList<Sense> getSenses() {
		return senses;
	}

	public void addSense(Sense sense) {
		this.senses.add(sense);
	}

	public String toString() {
		String result = word + " (" + senses.size() + "):\n";
		
		int i = 0;
		for (Sense s: senses) {
			result += "\tSENSE " + i++ + ":\n" + s + "\n";
		}
		return result;
	}
	

}
