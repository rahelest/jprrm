package wordnet;

import java.util.LinkedList;

public class WordNetResultProcessor extends Thread {
	
	final int HYPERNYMLEVEL = 5;
	
	enum Mode {
		SENSE, TOBEADDED, TOBESKIPPED;
	}
	
	LinkedList<String> hypernymTrees = new LinkedList<String>();
	
	@Override
	public void run() {
		while (true) {
			if (!hypernymTrees.isEmpty()) {
				parseHypernymTree();
			}
		}
		
	}

	private void parseHypernymTree() {
		String parsee = hypernymTrees.removeFirst();
		Mode mode = Mode.SENSE;
		
		String[] termArray = rows[0].split(" ");
		
		WordNetResult wnR = new WordNetResult(termArray[termArray.length - 1]);
		LinkedList<String> hypernyms = null;
		for (int i = 1; i < rows.length; i++) {
			
			switch(mode) {
			case SENSE:
				hypernyms = new LinkedList<String>();
				mode = Mode.TOBEADDED;
				break;
			case TOBEADDED:
				
				break;
			case TOBESKIPPED:
				
				break;
			default:
				throw new Exception("Unknown mode!");
			}
			
			if (rows[i].startsWith("Sense ")) {
				
			}
		}
		
		
		wnR.addHypernyms(hypernyms);
		
	}
	
	public void addHypernymTree(String hypTree) {
		hypernymTrees.addLast(hypTree);
	}

}
