package wordnet;

import java.util.LinkedList;

public class WordNetResultProcessor extends Thread {
	
	int HYPERNYMLEVEL = 5;
	final int HOMONYMS;
	
	enum Mode {
		SENSE, TOBEADDED, TOBESKIPPED;
	}
	
	LinkedList<String> hypernymTrees = new LinkedList<String>();
	
	public WordNetResultProcessor(int homonyms) {
		HOMONYMS = homonyms;
	}
	
	public WordNetResultProcessor(int homonyms, int hypernymlevel) {
		HOMONYMS = homonyms;
		HYPERNYMLEVEL = hypernymlevel;
	}
	
	@Override
	public void run() {
		while (true) {
			if (!hypernymTrees.isEmpty()) {
				parseHypernymTree();
			}
			
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	private void parseHypernymTree() {
		String parsee = hypernymTrees.removeFirst();
		Mode mode = Mode.SENSE;
		
		String[] rows = parsee.split("\n");
		String[] termArray = rows[0].split(" ");
		
		WordNetResult wnR = new WordNetResult(termArray[termArray.length - 1]);
		LinkedList<String> hypernyms = null;
		
		int hypernymLevel = 0;
		int homonyms = 0;
		
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
				System.out.println("UNKNOWN MODE");
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
