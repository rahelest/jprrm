package twitter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author Jaanus
 *
 */
public class TwitterAPI {
	/**
	 * @param location This consists of lat+lon+rng.
	 * @param sort This tells the function how to sort the results.
	 * @return This function returns the whole response from twitter.
	 * @throws IOException 
	 */
	public static String getTweets(String location, final int sort)
	throws IOException {
		String inputLine = null;

		int i = 0; //cycle-variable
		int pr = 0; //variable for splitting the returned string
		final int alg = 11;
		final int lpp = 15;
		final int lpp2 = 8;
		final int ptn = 14; //for the printing cycle
		//String pos = null; //position holder for splitting the returned string
		final int tnr = 15; //the number of returned tweets
		Atweet[] tweet = new Atweet[tnr];
		
		try {
			URL twitter = new URL(location);
			BufferedReader brIn = new BufferedReader(
					new InputStreamReader(
							twitter.openStream()));
			while ((inputLine = brIn.readLine()) != null) {
				
				for (i = 0; i < tnr; i++) {
				/**
				 * We create a new tweet and start finding contents for it.
				 */
				tweet[i] = new Atweet();				
				/**
				 * We locate the date of a tweet and save it.
				 */
				pr = inputLine.indexOf("_at\":\"");
				inputLine = inputLine.substring(pr + alg);
				pr = inputLine.indexOf("\",\"fr");
				tweet[i].setDate(inputLine.substring(0, pr - lpp));

				/**
				 * We locate the author of a tweet and save it.
				 */
				pr = inputLine.indexOf("rom_user\":\"");
				inputLine = inputLine.substring(pr + alg);
				pr = inputLine.indexOf("\",\"metadata\":{\"");
				tweet[i].setAuthor(inputLine.substring(0, pr));

				/**
				 * We locate the contents of a tweet and save them.
				 */
				pr = inputLine.indexOf("\"text\":\"");
				inputLine = inputLine.substring(pr + lpp2);
				pr = inputLine.indexOf("\",\"id\"");
				tweet[i].setContent(inputLine.substring(0, pr));
					}
				
				}
		brIn.close();
		} catch (MalformedURLException e) {
			System.out.println("ERROR! MALFORMED URL!");
			e.printStackTrace(); //This prints data prior to the exception.
		} finally {
			//brIn.close();
		}
		
		Atweet tempt = new Atweet(); //teeme veel ajutisi objekte, et sorteerida
		int j = 0;
		String[] temp = new String[tnr];
		
		switch(sort) {
		case 0: //as default is by date, this one does not sort, just prints.
			for (i = 0; i < tnr; i++) {
				System.out.print(tweet[i].getDate());
				System.out.print(" " + tweet[i].getAuthor());
				System.out.println(": " + tweet[i].getContent());
			}
			break;
		case 1: //sorts by name
			for (i = 0; i < ptn; i++) {
				for (j = i + 1; j < tnr; j++) {
					temp[i] = tweet[i].getAuthor();
					temp[j] = tweet[j].getAuthor();
					if (temp[i].compareToIgnoreCase(temp[j]) > 0) {
						tempt = tweet[i];
						tweet[i] = tweet[j];
						tweet[j] = tempt;
					}
				}
			}
			for (i = 0; i < tnr; i++) {
				System.out.print(tweet[i].getDate());
				System.out.print(" " + tweet[i].getAuthor());
				System.out.println(": " + tweet[i].getContent());
			}
			break;
		case 2: //sorts by content
			for (i = 0; i < ptn; i++) {
				for (j = i + 1; j < tnr; j++) {
					temp[i] = tweet[i].getContent();
					temp[j] = tweet[j].getContent();
					if (temp[i].compareToIgnoreCase(temp[j]) > 0) {
						tempt = tweet[i];
						tweet[i] = tweet[j];
						tweet[j] = tempt;
					}
				}
			}
			for (i = 0; i < tnr; i++) {
				System.out.print(tweet[i].getDate());
				System.out.print(" " + tweet[i].getAuthor());
				System.out.println(": " + tweet[i].getContent());
			}
			break;
		default: //To remove the Checkstyle error :D
			System.out.println("Sorting error,"
					+ " the program will now terminate.");
			System.exit(0);
			break;
		} //siin lõppeb switch
			
		
		return inputLine;
	}
}
