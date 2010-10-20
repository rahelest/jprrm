package sauts;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

/**
 *
 * @author Rahel
 *
 */
public class Main {

	/**
	 * @param args Linn.
	 */
	public static void main(final String[] args) {
		if (args.length == 0) {
			System.out.println("Säutsude "
				+ "lugemiseks palun sisesta otsitav piirkond.");
			System.exit(0);
		}
		String location, coordinates = null;
		location = args[0];
		final String tartu = "200,4,58.3708837,26.7148282";
		final String tallinn = "200,4,59.4388619,24.7544715";
		System.out.println("Säutsud linnast " + location);
		//coordinates = "200,4,59.4388619,24.7544715";
		/*try {
			coordinates = getResponse("http://maps.google.com"
					+ "/maps/geo?output=csv&q="
					+ location);
		} catch (IOException e) {
			System.out.println("Ei leidnud! (google)");
			System.exit(0);
			// e.printStackTrace();
		}*/
		System.out.println(tallinn);
		Coordinates c = setCoordinates(tallinn);
		try {
			getTweets("http://search.twitter.com/"
				+ "search.atom?geocode=" + c.getLa()
				+ "," + c.getLo() + ",10km");
		} catch (IOException e) {
			System.out.println("Ei leidnud! (twitter)");
			System.exit(0);
			e.printStackTrace();
		}
		int type;
		if (args [1].equals("name")) {type = 1;}
		else if (args [1].equals("date")) {type = 0;}
		else if (args [1].equals("content")) {type = 2;}
		else if (args [1] == null) {type = 0;}
	}

	/**
	 * Asks Google for coordinates.
	 * @param url The website.
	 * @return The coordinates.
	 * @throws IOException Reading exception.
	 */
	public static String getResponse(final String url) throws IOException {
		URL google = new URL(url);
		String co = null;
		InputStreamReader iSR =
			new InputStreamReader(google.openStream());
		BufferedReader bR = new BufferedReader(iSR);
		String inputLine;

		while ((inputLine = bR.readLine()) != null) {
		    co = inputLine;
		    System.out.println(co);
		}
		bR.close();

		return co;
	}
/**
 * Finds the necessary info from the string.
 * @param co The string to edit.
 * @return Sets the coordinates.
 */
	public static Coordinates setCoordinates(String co) {
		int pos; String la, lo;
		Coordinates c = new Coordinates();
		pos = co.indexOf(",", 1);
		if (co.lastIndexOf("200", 5) == -1) {System.exit(0);}
		pos = co.indexOf(",", pos + 1);
	//	System.out.println(pos);
		co = co.substring(pos + 1);
	//	System.out.println(co);
		pos = co.indexOf(",", 0);
		la = co.substring(0, pos); lo = co.substring(pos + 1);
		c.setLa(la);
		c.setLo(lo);
		System.out.println("la: " + c.la);
		System.out.println("lo: " + c.lo);
		return c;
	}
	/**
	 * @param url The site where to get the results.
	 * @throws IOException Reading exception.
	 */
	public static void getTweets(final String url) throws IOException {
		URL tweets = new URL(url);
		int pos = 0; final int tnr = 15;
		InputStreamReader iSR
		= new InputStreamReader(tweets.openStream());
		BufferedReader bR = new BufferedReader(iSR);
		String inputLine;
		Tweets[] tweet = new Tweets[tnr]; int i = 0;
		while ((inputLine = bR.readLine()) != null) {
			if (inputLine.indexOf("Twitter Search") != -1) {
					i = 0; continue;
			} else if ((pos = inputLine.indexOf("<published>")) != -1) {
				tweet[i] = new Tweets();

//	System.out.println("pos: " + pos + " I: " + inputLine);
				tweet[i].setDate(inputLine.substring(pos + 11, inputLine.indexOf("</published>")));
//				System.out.print("Date: " + tweet[i].getDate());
			} else if ((pos = inputLine.indexOf("<title>")) != -1) {
//	System.out.println("pos: " + pos + " I: " + inputLine);
				try {
					tweet[i].setText(inputLine.substring(pos + 7, inputLine.indexOf("</title>")));
				} catch (StringIndexOutOfBoundsException e) {
					System.out.println("Title error!");
				}
//	System.out.print("\tText: " + tweet[i].getText());
			}  else if ((pos = inputLine.indexOf("<name>")) != -1) {
//	System.out.println(inputLine);
				tweet[i].setName(inputLine.substring(pos + 6, inputLine.indexOf("(")));
//  System.out.println("\tName: " + tweet[i].getName());
				i++;
			}
		}
		for (i = 0; i < tnr; i++) {
			System.out.print(tweet[i].getDate());
			System.out.print(" " + tweet[i].getName());
			System.out.println(": " + tweet[i].getText());
			}
	}/**
 * Trimi võiks juurde panna, ei pea posi leidma, kuna alguse tühikuid pole.
 * Tallinn andis nüüd mingi errori? Ka Tartu, mõnikord.
 * täpitähed! - EI TÖÖTA
 * OK Esitusjärjekord! (Tegelikult hiljem)
 * Kood OK või ei? 200 on OK! (Et ta mitteleidmise korral tegutsema ei hakkaks.
 */

		bR.close();
	}
