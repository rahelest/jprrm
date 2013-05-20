package wordnet;

import java.util.ArrayList;
import java.util.Arrays;

public class HypernymLevel {

	ArrayList<String> hypernyms = new ArrayList<String>();

	public HypernymLevel(String hyps) {
		hyps = hyps.replaceAll("\\s", "");
		hypernyms.addAll(Arrays.asList(hyps.split(",")));
	}

	public void setHypernyms(String hyps) {
		hyps = hyps.replaceAll("\\s", "");
		hypernyms.addAll(Arrays.asList(hyps.split(",")));
	}

	public ArrayList<String> getHypernyms() {
		return hypernyms;
	}

	public String toString() {
		return hypernyms.toString();
	}

}
