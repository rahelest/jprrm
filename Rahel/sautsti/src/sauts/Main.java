package sauts;

import java.io.*;
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
//Declares the filenames of the files that are going to be read.
		String filename = "kohad.csv";
		File file = new File(filename);
		String filename2 = "dataen.txt";
		File file2 = new File(filename2);
		String location, coordinates = null;
		location = args[0];
		final String tartu = "200,4,58.3708837,26.7148282";
		final String tallinn = "200,4,59.4388619,24.7544715";
		System.out.println("Säutsud linnast " + location);
		boolean olemas = false;
		try {
			//otsi failist
			if (file.exists()) {
				FileReader fileReader =
					new FileReader(filename);
				BufferedReader brReader =
					new BufferedReader(fileReader);
				String line = brReader.readLine();
				if (line.indexOf(location) != -1) {
					System.out.println(line);
					olemas = true;
				}
				while (!olemas && line != null) {
					System.out.println(line);
					if (line.indexOf(location) != -1) {
						coordinates = line;
						System.out.println(coordinates);
						olemas = true; break;
					}
					line = brReader.readLine();
						//otsib välja koordinaadid
				} brReader.close();
			}
					if (!olemas) {
						coordinates = getResponse(
							"http://maps.google.com"
							+ "/maps/geo?output=csv"
							+ "&q="	+ location);
					}

		} catch (IOException e) {
			System.out.println("Ei leidnud! (google)");
			System.exit(0);
			// e.printStackTrace();
		}
		System.out.println(location);
		System.out.println(coordinates);
		if (olemas && coordinates.lastIndexOf("200", 3) == -1) {
			System.out.println("Ei leidnud sellist linna!");
			System.exit(0);
			}
		Coordinates c = setCoordinates(coordinates);
		int type = 0;
		if (args.length > 1) {
			if (args[1].equals("name")) { type = 1;
			} else if (args[1].equals("date")) { type = 0;
			} else if (args[1].equals("content")) { type = 2;
			} else {
				System.out.println("Pole sellist käsku!");
				System.exit(0);
				}
		}
		int r = 10;
		try {
			if (file2.exists()) {
				FileReader fileReader2 =
					new FileReader(filename2);
				BufferedReader brReader2 =
					new BufferedReader(fileReader2);
				String line = brReader2.readLine();
				while (line != null) {
					line = brReader2.readLine();
					if (line.indexOf(location) != -1) {
						System.out.println(line);
						for (int i = 0; i < 5; i++) {
							line = line.
							  substring(line.
							   indexOf("\t", 1));
							System.out.println(
							 i + ": " + line);
						}
						if (line.indexOf("\t") != -1) {
							line = line.
							  substring(0, line.
							   indexOf("\t", 1));
						}
						line = line.trim();
						System.out.println(line);
						break;
						}
						//otsib välja koordinaadid
					} brReader2.close();
				}
			getTweets("http://search.twitter.com/"
				+ "search.atom?geocode=" + c.getLa()
				+ "," + c.getLo() + "," + r + "km", type);
		} catch (IOException e) {
			System.out.println("Ei leidnud! (twitter)");
			System.exit(0);
			e.printStackTrace();
		}
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
		int pos; String la, lo; final int stc = 5;
		Coordinates c = new Coordinates();
		pos = co.indexOf(",", 1);
		pos = co.indexOf(",", pos + 1);
		System.out.println(pos);
		co = co.substring(pos + 1);
		System.out.println(co);
		pos = co.indexOf(",", 0);
		la = co.substring(0, pos); lo = co.substring(pos + 1);
		c.setLa(la);
		c.setLo(lo);
		System.out.println("la: " + c.la);
		System.out.println("lo: " + c.lo);
		return c;
	}
	/** @param type How to sort.
	 * @param url The site where to get the results.
	 * @throws IOException Reading exception.
	 */
	public static void getTweets(final String url,
			final int type) throws IOException {
		URL tweets = new URL(url);
		int pos = 0; final int tnr = 15; final int nnr = 6;
		final int tinr = 7; final int dnr = 11; boolean title = false;
		InputStreamReader iSR
		= new InputStreamReader(tweets.openStream());
		BufferedReader bR = new BufferedReader(iSR);
		String inputLine;
		Tweets[] tweet = new Tweets[tnr]; int i = 0;
		while ((inputLine = bR.readLine()) != null) {
			if (inputLine.indexOf("Twitter Search") != -1) {
					i = 0; continue;
			} else if ((inputLine.indexOf("<published>")) != -1) {
				tweet[i] = new Tweets();
				pos = inputLine.indexOf("<published>");
//	System.out.println("pos: " + pos + " I: " + inputLine);
				tweet[i].setDate(inputLine.substring(pos + dnr,
					inputLine.indexOf("</published>")));
//				System.out.print("Date: " + tweet[i].getDate());
			} else if ((inputLine.indexOf("<title>")) != -1
					|| title) {
//	System.out.println("pos: " + pos + " I: " + inputLine);
				pos = inputLine.indexOf("<title>");
				if (inputLine.indexOf("</title>") == -1) {
					title = true;
					tweet[i].setText(inputLine.substring(pos
							+ tinr));
				} else {
					try {
						tweet[i].setText(inputLine.
							substring(pos + tinr,
							inputLine.
							indexOf("</title>")));
							title = false;
					} catch (StringIndexOutOfBoundsException e) {
						System.out.
						println("Title error!");
					}
				}
//	System.out.print("\tText: " + tweet[i].getText());
			}  else if ((inputLine.indexOf("<name>")) != -1) {
				pos = inputLine.indexOf("<name>");
//	System.out.println(inputLine);
				tweet[i].setName(inputLine.substring(pos
						+ nnr, inputLine.indexOf("(")));
//  System.out.println("\tName: " + tweet[i].getName());
				i++;
			}
		}
		bR.close();
		Tweets tempt = new Tweets(); int j = 0;
		String[] tw = new String[tnr];
		switch (type) {
		case 0:
			for (i = 0; i < tnr; i++) {
				System.out.print(tweet[i].getDate());
				System.out.print(" " + tweet[i].getName());
				System.out.println(": " + tweet[i].getText());
			}
			break;
		case 1:
			for (i = 0; i < tnr - 1; i++) {
				for (j = i + 1; j < tnr; j++) {
					tw[i] = tweet[i].getName();
					tw[j] = tweet[j].getName();
					if (tw[i].
					compareToIgnoreCase(tw[j]) > 0) {
						tempt = tweet[i];
						tweet[i] = tweet[j];
						tweet[j] = tempt;
					}
				}
			}
			for (i = 0; i < tnr; i++) {
				System.out.print(tweet[i].getDate());
				System.out.print(" " + tweet[i].getName());
				System.out.println(": " + tweet[i].getText());
			}
			break;
		case 2:
			for (i = 0; i < tnr - 1; i++) {
				for (j = i + 1; j < tnr; j++) {
					tw[i] = tweet[i].getText();
					tw[j] = tweet[j].getText();
					if (tw[i].
					compareToIgnoreCase(tw[j]) > 0) {
						tempt = tweet[i];
						tweet[i] = tweet[j];
						tweet[j] = tempt;
					}
				}
			}
			for (i = 0; i < tnr; i++) {
				System.out.print(tweet[i].getDate());
				System.out.print(" " + tweet[i].getName());
				System.out.println(": " + tweet[i].getText());
			}
			break;
		default:
			System.out.println("Viga!");
			System.exit(0);
			break;
		}
	}
/**
 * Trimi võiks juurde panna, ei pea posi leidma, kuna alguse tühikuid pole.
 * Tallinn andis nüüd mingi errori? Ka Tartu, mõnikord.
 * täpitähed! - EI TÖÖTA
 * OK Esitusjärjekord! (Tegelikult hiljem)
 * Kood OK või ei? 200 on OK! (Et ta mitteleidmise korral tegutsema ei hakkaks.
 */

	}
