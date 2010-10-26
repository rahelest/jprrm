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
	/**
	 * @param loc This is the local variable for the desired location.
	 * @return This function returns latitude and longitude of the requested 
	 * location.
	 * @throws IOException On mingi suvakas error:D
	 */
	public static String getResponse(String loc) throws IOException {
		
		String inputLine = null;
		
		try {
			URL google = new URL(loc);
			BufferedReader brIn = new BufferedReader(
					new InputStreamReader(
					google.openStream()));

			while ((inputLine = brIn.readLine()) != null) {
				//System.out.println(inputLine);
				return inputLine;
				}
			brIn.close();
		} catch (MalformedURLException e) {
			System.out.println("ERROR! MALFORMED URL!");
			e.printStackTrace(); //This prints data prior to the exception.
		} finally {
			//brIn.close();
		}
		return inputLine;
	}

}
