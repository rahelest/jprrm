/**
 * 
 */
package twitter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * @author Jaanus
 *
 */
public class Twittermain {

	/**
	 * @param args This function needs the name of the city as an argument.
	 * @throws IOException Ei tea miks.
	 */
	public static void main(String[] args) throws IOException {
		String location;
		final int lata = 6;
		final int latl = 15;
		final int lona = 17;
		final int lonl = 26;
		final int numb = 40; //mõtiskle
		int mode = 0; //how to sort the results
		int radius = 0; // radius calculated from the dataen.txt		
		String longitude = null;
		String latitude = null;
		String googleResp = null;
		String altnimed = "";
		String filename = "kohad.csv";
		File file = new File(filename);
		String filename2 = "dataen.txt";
		File file2 = new File(filename2);
		boolean olemas = false;
		//System.out.println(args[1]);
		/**
		 * We determine the appropriate sorting method.
		 */
		if (args.length == 2) {
			if (args[1].equals("name")) {
				mode = 1;
			} else if (args[1].equals("content")) {
				mode = 2;
			} else if (args[1].equals("date")) {
				mode = 0;
			} else {
				System.out.println("Invalid command, using default. (date)");
				mode = 0;
			}
		}
		
		if (args.length == 0) {
			System.out.println("Please use commandline arguments! "
			+ "Format: [desired location] [desired radius]");
			System.exit(0);
		} else {
			/**
			* This takes the first command line argument.
			*/
			
		location = args[0];
		
		System.out.println("Tweets from: " + location);
			
		/**
		 * We ask if the user wants to add alternative names,
		 * and then save them to a string
		 */
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		System.out.print("Kas tahad sellele kohale alternatiivnimetusi "
				+ "anda? Y/N  ");
		if (br.readLine().equals("Y")) {
			System.out.print("Sisesta alternatiivnimed (formaadis: "
					+ "nimi1,nimi1,nimi3 jne):");
			altnimed = br.readLine();
		}
		
		if (file2.exists()) {
				FileReader fileReader2 = new FileReader(filename2);
				BufferedReader brReader2 = new BufferedReader(fileReader2);
				String line = brReader2.readLine();
				final int tabs = 5; int pos;
				while (line != null) {
					line = brReader2.readLine();
					pos = line.indexOf("\t");
					/**
					 * We remove all \t-s in front of the city's name
					 */
					for (int i = 0; i < tabs; i++) {
						pos = line.indexOf("\t", pos + 1);
					}
					if (line.lastIndexOf((location + "\t"), pos) != -1) {
						for (int i = 0; i < tabs; i++) {
							line = line.substring(line.indexOf("\t", 1));
						}
						if (line.indexOf("\t") != -1) {
							line = line.substring(0, line.indexOf("\t", 1));
						}
						/**
						 * now we remove whitespaces that are not needed
						 */
						line = line.trim();
						radius = Integer.parseInt(line);
						/**
						 * now we calculate the required radius
						 */
						radius = (int) (Math.sqrt(radius) / numb);
						break;
						}
					} brReader2.close();
				}
			
		
		try {
			if (file.exists()) {
				FileReader fileReader =	new FileReader(filename);
				BufferedReader brIn = new BufferedReader(fileReader);
				String line = brIn.readLine();
				if (line != null && line.indexOf(location) != -1) {
						googleResp = line;
						olemas = true;
				}
				while (!olemas && line != null) {
							if (line.indexOf(location) != -1) {
								googleResp = line;
								olemas = true; 
								break;
							}
							line = brIn.readLine();
						} brIn.close();
			if (!olemas) { //if location is not found from the file
					googleResp = GoogleAPI.getResponse("http://maps.google.com"
						+ "/maps/geo?output=csv&q="
						+ location);
						latitude = googleResp.substring(lata, latl);
						longitude = googleResp.substring(lona, lonl);
						/**
						 * If the location doesn't exist in the cache,
						 * we save it there
						 */
						FileWriter fileWriter = new FileWriter(filename, true);
						PrintWriter prWriter = new PrintWriter(fileWriter);
						prWriter.println(location + "," + latitude + ","
								+ longitude + "," + radius + "," + altnimed);
						fileWriter.close();
							}					
					}

			} catch (IOException e) {
				System.out.println("Network error or location not found!\n"
						+ " The program will now teminate.");
				System.exit(0);
				}
			
			/**
			 * now we turn to twitter
			 */			
			try {
			TwitterAPI.getTweets("http://search.twitter.com/"
					+ "search.json?geocode=" + latitude + "," + longitude
					+ "," + radius + "km", mode);
			} catch (IOException e) {
				System.out.println("Network error or location not found!\n"
						+ " The program will now teminate.");
				System.exit(0);
			}
		}
		//System.out.println(googleResp);
		//System.out.println(twitterResp);
		//System.out.println("longitude: " + longitude);
		//System.out.println("latitude: " + latitude);
	}
	
}
