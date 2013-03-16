package flickr;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.aetrion.flickr.Flickr;
import com.aetrion.flickr.FlickrException;
import com.aetrion.flickr.REST;
import com.aetrion.flickr.RequestContext;
import com.aetrion.flickr.auth.Auth;
import com.aetrion.flickr.auth.Permission;
import com.aetrion.flickr.photos.Extras;
import com.aetrion.flickr.photos.Photo;
import com.aetrion.flickr.photos.PhotoList;
import com.aetrion.flickr.photos.PhotosInterface;
import com.aetrion.flickr.photos.SearchParameters;
import com.aetrion.flickr.tags.Tag;
import com.aetrion.flickr.util.IOUtilities;

public class FlickrSearch {
    static String apiKey;
    static String sharedSecret;
    Flickr f;
    REST rest;
    RequestContext requestContext;
    Properties properties = null;

    public FlickrSearch()
      throws ParserConfigurationException, IOException {
        InputStream in = null;
        try {
            in = getClass().getResourceAsStream("setup.properties");
            properties = new Properties();
            properties.load(in);
        } finally {
            IOUtilities.close(in);
        }
        f = new Flickr(
            properties.getProperty("apiKey"),
            properties.getProperty("secret"),
            new REST()
        );
        requestContext = RequestContext.getRequestContext();
        Auth auth = new Auth();
        auth.setPermission(Permission.READ);
        auth.setToken(properties.getProperty("token"));
        requestContext.setAuth(auth);
        Flickr.debugRequest = false;
        Flickr.debugStream = false;
    }

    public void getGeoData() throws FlickrException, IOException, SAXException {

        
//        System.out.println(requestContext.getRequestContext());
        
        
        PhotosInterface ph = f.getPhotosInterface();
        
        SearchParameters searchParam = new SearchParameters();
        searchParam.setHasGeo(true);
        Set extras = new HashSet();
        extras.add(Extras.GEO);
        extras.add(Extras.TAGS);
        searchParam.setExtras(extras);
        
        
        Photo photo = null;
        
        int realI = 0;
        String output = "";
        for (int j = 200; j <= 200; j++) {
        	
	        PhotoList rec = ph.search(searchParam, 500, j);
	        for (int i= 0; i < rec.size(); i++) {
	        	photo = (Photo) rec.get(i);
	        	output = realI + ": " + photo.getGeoData() + ": ";
	        	for (Object ot : photo.getTags()) {
	        		
	        		Tag t = (Tag) ot;
	        		output += t.getValue() + ", ";
	        		
	        	}
	        	System.out.println();
	        	realI++;
	        }
        }
        /*System.out.println(rec.getPage());
        System.out.println(rec.getPages());
        System.out.println(rec.getPerPage());
        System.out.println(rec.getTotal());
        System.out.println(rec.get(0));*/
        
        /*
        System.out.println(searchParam.getTagMode());
        System.out.println(ph.search(searchParam, 50, 1));*/
    }

    public static void main(String[] args) {
        try {
            FlickrSearch t = new FlickrSearch();
            t.getGeoData();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.exit(0);
    }

}