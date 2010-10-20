/**
 * 
 */
package sauts;

import java.io.*;

/**
 * @author Rahel
 *
 */
public class Failitamine {

	/**
	 * @param args nuffin
	 * @throws IOException jama
	 */
	public static void main(final String[] args) throws IOException {
		// TODO Auto-generated method stub
		String filename = "kohad.csv";
		File file = new File(filename);
		try { file.createNewFile(); } catch (IOException e) {
			System.out.println("Viga!");
		}
		System.out.println(file.getAbsolutePath());
		   FileReader file_reader = new FileReader(filename);
		//   FileWriter file_writer = new FileWriter(copyName);
		   BufferedReader br_reader = new BufferedReader(file_reader);
		 //  PrintWriter pr_writer = new PrintWriter(file_writer);

		   // Get the first line of the file
		   String line = br_reader.readLine(); 
			System.out.println(line);
			line = br_reader.readLine(); 
			System.out.println(line);
	}

}
