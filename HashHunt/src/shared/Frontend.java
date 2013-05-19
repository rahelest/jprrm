package shared;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import flickr.FlickrSearch;

import twitter.TwitterTest;
import twitter4j.TwitterException;

public class Frontend {	
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		
		Writer writer = new Writer();
		Chopshop chopShop = new Chopshop(writer);
		TwitterTest twitter = new TwitterTest(chopShop);
//		FlickrSearch flickr = new FlickrSearch(chopShop);
		
		while (true) {
			Scanner scanner = new Scanner(System.in);
			System.out.print("> ");
			String command = scanner.next();

			switch (command) {
			case "start":
				twitter.run();
				continue;
			case "stop":
				try {
					twitter.stop();
				} catch (TwitterException e) {
					// TODO Auto-generated catch block
					System.out.println("Viga twitteri peatamisel, uuri ja paranda.");
					e.printStackTrace();
				}
				continue;
			case "exit":

				break;
			default:
				System.out.println("Unknown command! (start, stop, exit)");
				continue;
			}
		}
	}	
}
