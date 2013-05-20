package shared;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import otter.OtterCaller;
import otter.OtterInputGenerator;

import flickr.FlickrQueue;
import flickr.FlickrSearch;

import twitter.TwitterQueue;
import twitter.TwitterTest;
import twitter4j.TwitterException;

public class Frontend {	
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		
		Writer writer = new Writer();
		TwitterQueue tq = new TwitterQueue();
		FlickrQueue fq = new FlickrQueue();
		Chopshop tChopShop = new Chopshop(writer, tq);
		Chopshop fChopShop = new Chopshop(writer, fq);
		TwitterTest twitter = new TwitterTest(tChopShop);
		FlickrSearch flickr = new FlickrSearch(fChopShop);
		
		OtterInputGenerator oig = new OtterInputGenerator(fq, tq);
		
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
