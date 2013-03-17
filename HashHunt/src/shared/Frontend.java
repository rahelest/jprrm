package shared;

import java.util.Scanner;

import twitter.TwitterTest;
import twitter4j.TwitterException;

public class Frontend {
	public static void main(String[] args) {
		TwitterTest twitter = new TwitterTest();
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
