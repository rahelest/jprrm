package wordnet;

import java.util.ArrayList;
import java.util.LinkedList;

public class WordNetResultProcessor extends Thread {
	
	int HYPERNYMLEVEL = 5;
	int HOMONYMS = 5;
	
	LinkedList<String> hypernymTrees = new LinkedList<String>();
	final Object lock = new Object();
	
	enum Mode {
		SYNONYMS,
		HYPERNYMS;
	}
	
	
	public WordNetResultProcessor() {
		this.start();
	}
	
	public WordNetResultProcessor(int homonyms) {
		HOMONYMS = homonyms;
		this.start();
	}
	
	public WordNetResultProcessor(int homonyms, int hypernymlevel) {
		HOMONYMS = homonyms;
		HYPERNYMLEVEL = hypernymlevel;
		this.start();
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
				e.printStackTrace();
			}
		}
		
	}

	private void parseHypernymTree() {
		String parsee = null;
		synchronized (lock) {
			parsee = hypernymTrees.removeFirst();
		}
		Mode mode = Mode.SYNONYMS;
		
		String[] rows = parsee.split("\n");
		System.out.println(rows[1]);
		String[] termArray = rows[1].split(" ");
		
		WordNetResult wnR = new WordNetResult(termArray[termArray.length - 1]);
		System.out.println(wnR.word);
		ArrayList<String> hypernyms = new ArrayList<String>();
		
		int hypernymLevel = 0;
		int homonymNumber = 0;
		
		for (int i = 6; i < rows.length && homonymNumber <= HOMONYMS; i++) {;
			switch(mode) {
			case SYNONYMS:
				hypernyms.add(rows[i]);
				mode = Mode.HYPERNYMS;
				break;
			case HYPERNYMS:
				if (!rows[i].startsWith("Sense ")) {
					int equalSignIndex = rows[i].indexOf("=>");
					if (equalSignIndex >= 0) {
						String row = rows[i].substring(equalSignIndex + 3);
						hypernymLevel = (equalSignIndex) / 4;
						
						if (hypernymLevel <= HYPERNYMLEVEL) {
							if (hypernyms.size() - 1 < hypernymLevel) {
								hypernyms.add(row);
							} else {
								String hyp = hypernyms.get(hypernymLevel);
								hypernyms.set(hypernymLevel, hyp + ", " + row);
							}
						}
					}
				} else {
					homonymNumber++;					
					wnR.addHypernyms((ArrayList<String>) hypernyms.clone());
					hypernyms.clear();
					mode = Mode.SYNONYMS;
				}
				break;
			default:
				System.out.println("UNKNOWN MODE");
			}
		}
		
		System.out.println(wnR);
	}
	
	public void addHypernymTree(String hypTree) {
		synchronized (lock) {
			hypernymTrees.addLast(hypTree);
		}
		
	}

}
