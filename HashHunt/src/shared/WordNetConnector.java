package shared;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class WordNetConnector {
	
	

	public WordNetConnector() {

	}

	public static void main(String[] args) throws IOException {
		WordNetConnector wnC = new WordNetConnector();
		System.out.println(wnC.getWord("http://wordnetweb.princeton.edu/perl/webwn?o2=&o0=1&o7=&o5=&o1=1&o6=&o4=&o3=&s=trope&h=10000&j=4#c"));
	}

}
