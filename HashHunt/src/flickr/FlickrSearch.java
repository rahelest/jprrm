package flickr;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import shared.Writer;

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

public class FlickrSearch implements Runnable {
    static String apiKey;
    static String sharedSecret;
    Flickr f;
    REST rest;
    RequestContext requestContext;
    Properties properties = null;
    
    PhotosInterface ph = null;
    SearchParameters searchParam = null;
    Photo photo = null;
    int realI = 0;
    int pageNum = 1;
    String output = "";
    Boolean running = true;
    PhotoList rec = null;
    int totalPages;
    
    Writer writer = new Writer();

    public FlickrSearch()
      throws ParserConfigurationException, IOException, SAXException, FlickrException {
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
        
        ph = f.getPhotosInterface();
        
        searchParam = new SearchParameters();
        searchParam.setHasGeo(true);
        searchParam.setMinUploadDate(new Date(1,3,2013));
        Set<String> extras = new HashSet<String>();
        
        extras.add(Extras.DATE_UPLOAD);
        extras.add(Extras.GEO);
        extras.add(Extras.TAGS);
        searchParam.setExtras(extras);
        
        
        rec = ph.search(searchParam, 200, 0);
        System.out.println("GetPages: " + rec.getPages());
        System.out.println("Total: " + rec.getTotal());
        totalPages = rec.getPages();
        
        this.run();
    }
    
	@Override
	public void run() {
		
		while(running && pageNum <= totalPages) {
        	
	        for (int i= 0; i < rec.size(); i++) {
	        	photo = (Photo) rec.get(i);
	        	output = photo.getGeoData().getLatitude() + ";" + photo.getGeoData().getLongitude() + ";";
	        	for (Object ot : photo.getTags()) {
	        		
	        		Tag t = (Tag) ot;
	        		output += t.getValue() + ", ";
	        		
	        	}
	        	writer.writeToFile(output);
	        	realI++;
	        }
	        if (pageNum % 1000 == 0) 
	        	System.out.print(Math.floor(pageNum/1000) + " ");
        	rec.setPage(++pageNum);
		}
	}
	
	public void stop() {
		running = false;
	}

    public static void main(String[] args) {
        try {
            new FlickrSearch();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.exit(0);
    }
}