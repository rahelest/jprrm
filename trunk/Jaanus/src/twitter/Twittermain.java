/**
 * 
 */
package twitter;

/**
 * @author Jaanus
 *
 */
public class Twittermain {

	/**
	 * @param args This function needs the name of the city as an argument.
	 */
	public static void main(String[] args) {
		try{
			if (args.length == 0) {
				System.out.println("Palun kasuta k�surea argumente!");
				System.exit(0);
			} else {
				String location;
				// v�tan esimese k�surea argumendi
				location = args[0];
				System.out.println("S�utsud linnast: " + location);
				String googleResponse = getResponse("http://maps.google.com"
						+ "/maps/geo?output=csv&q="
						+ location);
			
				Coordinates c = getCoordinatesFromRow(googleResponse);			

			}
		} finally {
			/**
			 * kirjuta k�ige sulgemine
			 */
		}
	}

}
