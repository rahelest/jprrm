package shared;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SystemCaller {
	
	
	public static void main(String[] args) {
		
		try { 
			Process p=Runtime.getRuntime().exec("cmd /c dir"); 
			p.waitFor(); 
			BufferedReader reader=new BufferedReader(new InputStreamReader(p.getInputStream())); 
			String line=reader.readLine(); 
			while(line!=null) { 
				System.out.println(line); 
				line=reader.readLine(); 
			} 
		
		} catch(IOException e1) {
		} catch(InterruptedException e2) {} 
		
		System.out.println("Done"); 
	} 

}