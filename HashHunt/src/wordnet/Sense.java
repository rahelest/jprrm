package wordnet;

import java.util.ArrayList;
import java.util.Arrays;

public class Sense implements Cloneable {

	ArrayList<String> synonyms = new ArrayList<String>();
	ArrayList<HypernymLevel> hypernyms = new ArrayList<HypernymLevel>();

	public ArrayList<String> getSynonyms() {
		return synonyms;
	}

	public void setSynonyms(String synonymString) {
		synonymString = synonymString.replaceAll("\\s", "");
		this.synonyms.addAll(Arrays.asList(synonymString.split(",")));
	}

	public ArrayList<HypernymLevel> getHypernyms() {
		return hypernyms;
	}
	
	public ArrayList<String> getAllHypernyms() {
		ArrayList<String> hyps = new ArrayList<>();
		for (HypernymLevel h : hypernyms) {
			hyps.addAll(h.getHypernyms());
		}
		return hyps;
	}

	public void addHypernyms(int level, String hyps) {
		if (hypernyms.size() <= level) {
			hypernyms.add(new HypernymLevel(hyps));
		} else {
			hypernyms.get(level).setHypernyms(hyps);
		}
	}

	public String toString() {
		String hyps = "";
		for (int i = 0; i < hypernyms.size(); i++) {
			hyps += "\t\t\tLevel " + i + ": " + hypernyms.get(i) + "\n";
		}
		return "\t\tSYNO: " + synonyms + "\n\t\tHYPER: \n" + hyps;
	}

	public Sense clone() {
		try {
			return (Sense) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;

	}
}
