package flickr;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import shared.*;
import com.aetrion.flickr.*;
import com.aetrion.flickr.auth.*;
import com.aetrion.flickr.photos.*;
import com.aetrion.flickr.tags.Tag;

public class FlickrSearch implements Runnable {

	Flickr f = null;
	Boolean running = true;
	Chopshop chopper = null;

	public FlickrSearch(Chopshop chopShop) {
		/*
		 * Seda kasuta kirjutamiseks, üks tulemus sööda meetodisse
		 * stringify(string)
		 */
		this.chopper = chopShop;
		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream(
					"config\\flickr.properties"));
		
		f = new Flickr(properties.getProperty("apiKey"),
					properties.getProperty("secret"), new REST());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RequestContext requestContext = RequestContext.getRequestContext();
		Auth auth = new Auth();
		auth.setPermission(Permission.READ);
		auth.setToken(properties.getProperty("token"));
		requestContext.setAuth(auth);
		Flickr.debugRequest = false;
		Flickr.debugStream = false;

		this.run();
	}

	@SuppressWarnings("deprecation")
	private PhotoList doSearch() throws IOException, SAXException,
			FlickrException {

		PhotosInterface ph = f.getPhotosInterface();
		SearchParameters searchParam = new SearchParameters();

		searchParam.setHasGeo(true);
		searchParam.setMinUploadDate(new Date(1, 3, 2013));
		Set<String> whatToShow = new HashSet<String>();

		whatToShow.add(Extras.DATE_UPLOAD);
		whatToShow.add(Extras.GEO);
		whatToShow.add(Extras.TAGS);
		searchParam.setExtras(whatToShow);

		PhotoList photoList = ph.search(searchParam, 200, 0);

		System.out.println("GetPages: " + photoList.getPages());
		System.out.println("Total: " + photoList.getTotal());

		return photoList;

	}

	@Override
	public void run() {

		Photo photo = null;
		int pageNum = 1;
		String output = "";

		PhotoList rec;
		try {
			rec = doSearch();
		} catch (IOException | SAXException | FlickrException e) {
			System.out.println("Probleem otsingu teostamisel, katkestan: "
					+ e.getMessage());
			e.printStackTrace();
			return;
		}
		int totalPages = rec.getPages();

		while (running && pageNum <= totalPages) {

			for (int i = 0; i < rec.size(); i++) {
				photo = (Photo) rec.get(i);
				// output = photo.getGeoData().getLatitude() + ";"
				// + photo.getGeoData().getLongitude() + ";";
				String URL;
				URL = photo.getUrl();
				for (Object ot : photo.getTags()) {

					Tag t = (Tag) ot;
					output += "#" + t.getValue() + " ";

				}
				// TODO kirjuta siia kolmandaks kellaaeg ja neljandaks
				// kasutajanimi
				chopper.stringify(photo.getGeoData().getLatitude() + " "
						+ photo.getGeoData().getLongitude() + " " + URL + " "
						+ output);
			}
			if (pageNum % 1000 == 0)
				System.out.print(Math.floor(pageNum / 1000) + " ");
			rec.setPage(++pageNum);
		}
	}

	public void stop() {
		running = false;
	}

	public static void main(String[] args) {
		try {
//			new FlickrSearch(new Chopshop(new Writer(), new FlickrSearch(chopShop)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.exit(0);
	}
}