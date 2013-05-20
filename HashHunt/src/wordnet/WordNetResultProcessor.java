package wordnet;

import java.util.LinkedList;

public class WordNetResultProcessor extends Thread {

	static int HYPERNYMLEVEL = 3;
	static int HOMONYMS = 1;

	LinkedList<String> hypernymTrees = new LinkedList<String>();
	final Object lock = new Object();
	final Object lock2 = new Object();

	LinkedList<WordNetResult> parsedWords = new LinkedList<>();

	enum Mode {
		SYNONYMS, HYPERNYMS;
	}

	public WordNetResultProcessor() {
//		this.start();
	}

	public WordNetResultProcessor(int homonyms) {
		HOMONYMS = homonyms;
//		this.start();
	}

	public WordNetResultProcessor(int homonyms, int hypernymlevel) {
		HOMONYMS = homonyms;
		HYPERNYMLEVEL = hypernymlevel;
//		this.start();
	}

	@Override
	public void run() {
		while (true) {
			if (!hypernymTrees.isEmpty()) {
//				parseHypernymTree();
			}

			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
	
	public static WordNetResult parseHypernymTree(String parsee) {
		
//		String parsee = null;
//		synchronized (lock) {
//			parsee = hypernymTrees.removeFirst();
//		}
		Mode mode = Mode.SYNONYMS;

		String[] rows = parsee.split("\n");
		System.out.println(rows[1]);
		String[] termArray = rows[1].split(" ");

		WordNetResult wnR = new WordNetResult(termArray[termArray.length - 1]);
		System.out.println(wnR.word);

		Sense sense = new Sense();

		int hypernymLevel = 0;
		int homonymNumber = 0;

		for (int i = 6; i < rows.length && homonymNumber < HOMONYMS; i++) {
			
			switch (mode) {
			case SYNONYMS:
				sense.setSynonyms(rows[i]);
				mode = Mode.HYPERNYMS;
				break;
			case HYPERNYMS:
				if (!rows[i].startsWith("Sense ")) {
					int equalSignIndex = rows[i].indexOf("=>");
					if (equalSignIndex >= 0) {
						String row = rows[i].substring(equalSignIndex + 3);
						hypernymLevel = equalSignIndex / 4 - 1;

						if (hypernymLevel < HYPERNYMLEVEL) {
							sense.addHypernyms(hypernymLevel, row);
						}
					}
				} else {
					homonymNumber++;
					wnR.addSense((Sense) sense.clone());
					sense = new Sense();
					mode = Mode.SYNONYMS;
				}
				break;
			default:
				System.out.println("UNKNOWN MODE");
			}
		}

		// System.out.println(wnR);
//		synchronized (lock2) {
//			parsedWords.add(wnR);
//		}
		return wnR;
	}

	public void addHypernymTree(String hypTree) {
		synchronized (lock) {
			hypernymTrees.addLast(hypTree);
		}

	}

	public WordNetResult getParsedWord() {
		boolean empty;
		synchronized (lock2) {
			empty = parsedWords.isEmpty();
		}

		while (empty) {
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			synchronized (lock2) {
				empty = parsedWords.isEmpty();

			}
		}
		synchronized (lock2) {
			return parsedWords.removeFirst();
		}

	}
}
