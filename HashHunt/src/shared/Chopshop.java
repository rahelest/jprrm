package shared;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Chopshop {
	
	private Writer writer = null;
	SocialStreamQueue ssq = null;
	
	public Chopshop(Writer writer, SocialStreamQueue ssq) throws FileNotFoundException, IOException {
		this.writer = writer;
		this.ssq = ssq;
		WordFilter.initialize();
	}

	public void stringify(String data) {
		data = data.trim();
		String result = "";
		data = data.replaceAll("[\\p{P}\\p{N}\\p{S}]", "");
		
		String[] words = data.split(" ");
		for (String word : words) {
			 if (!word.matches("\\A\\p{ASCII}*\\z")) {
				 continue;
			 }
			
			if (WordFilter.checkSuitability(word)) {
				result += word + ",";
			} 
		}		
//		writer.addToQueue(result);
		while (!ssq.addToQueue(result)) {
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(result);
	}


}
