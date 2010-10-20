/**
 * 
 */
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
public class GoogleAPI {
	private String latitude;
	private String longitude;
	
	public String getResponse(String loc) throws IOException {
		URL google = null;
		try {
			google = new URL(loc);
		} catch (MalformedURLException e) {
			System.out.println("VIGANE URL");
			e.printStackTrace();
		}
		BufferedReader in = new BufferedReader(
					new InputStreamReader(
					google.openStream()));
		String inputLine;
		while ((inputLine = in.readLine()) != null) {
		    System.out.println(inputLine);
		in.close();
		}
		return loc;
		
	}
}
