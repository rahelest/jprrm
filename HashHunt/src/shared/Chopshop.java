package shared;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Chopshop {
	
	private Writer writer = null;
	
	public Chopshop(Writer writer) throws FileNotFoundException, IOException {
		this.writer = writer;
		WordFilter.initialize();
	}

	public void stringify(String data) {
		data = data.trim();
		String result = "";
		
		String[] words = data.split(" ");
		for (String word : words) {
			if (WordFilter.checkSuitability(word)) {
				result += "\"" + word + "\"" + ",";
			} else System.out.println("Found one: " + word);
		}		
//		writer.addToQueue(result);
		System.out.println(result);
	}


}
