/**
 * 
 */
package twitter;

import java.io.IOException;

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
		String longitude = null;
		String latitude = null;
		String googleResp = null;
		
		//System.out.println(args[0]);
		
		if (args.length == 0) {
			System.out.println("Please use commandline arguments! "
			+ "Format: [desired location] [desired radius]");
			System.exit(0);
		} else {
			/**
			* This takes the first commandline argument.
			*/
			
			location = args[0];
			System.out.println("Tweets from: " + location);
			googleResp = GoogleAPI.getResponse("http://maps.google.com"
			+ "/maps/geo?output=csv&q="
			+ location);
			//longitude = googleResp.substring(6, 15);
			//latitude = googleResp.substring(17, 26);
		}
		System.out.println(googleResp);
		//System.out.println("longitude: " + longitude);
		//System.out.println("latitude: " + latitude);
		
	}

}
