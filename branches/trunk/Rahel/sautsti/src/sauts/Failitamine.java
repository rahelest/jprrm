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
		String filename = "C:\\Documents and Settings\\Rahel\\My Documents\\doc ja pdf\\Javaproge\\java2\\sautsti\\kohad.csv";
		String filename2 = "C:\\Documents and Settings\\Rahel\\My Documents\\doc ja pdf\\Javaproge\\java2\\sautsti\\dataen.txt";
		File file = new File(filename);
		File file2 = new File(filename2);
	//	try { file.createNewFile(); } catch (IOException e) {
	//		System.out.println("Viga!");
	//	}
		System.out.println(file.getAbsolutePath());
		 //  FileReader file_reader = new FileReader(filename);
		   FileWriter file_writer = new FileWriter(filename, true);
		//   BufferedReader br_reader = new BufferedReader(file_reader);
		   PrintWriter pr_writer = new PrintWriter(file_writer);

		   // Get the first line of the file
		
			pr_writer.println("kuku");

	//		line = br_reader.readLine(); 
	//		System.out.println(line);
			   file_writer.close();
			   
		FileReader file_reader = new FileReader(filename);
		BufferedReader br_reader = new BufferedReader(file_reader);
		String line = br_reader.readLine(); 
		do {
			System.out.println(line);
			line = br_reader.readLine(); 
		} while (line != null);
		br_reader.close();
		FileReader file_reader2 = new FileReader(filename2);
		BufferedReader br_reader2 = new BufferedReader(file_reader2);
		line = br_reader2.readLine(); 
		System.out.println(line);
		for (int i = 0; i < 5; i++) {
			line = line.substring(line.indexOf("\t", 1));
			System.out.println(i + ": " + line);
		}
		System.out.println(line.trim());
	}
}
