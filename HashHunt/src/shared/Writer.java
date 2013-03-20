package shared;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Writer {

	// TODO kirjuta puhver ja perioodiline tükeldamine
	FileWriter fstream = null;
	BufferedWriter out = null;

	public Writer() {
		DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
		Date date = new Date();
		try {
			fstream = new FileWriter("data_" + dateFormat.format(date) + ".csv");
			out = new BufferedWriter(fstream);

		} catch (Exception e) {
			System.err.println("Ei saanud streami avada: " + e.getMessage());
		}
	}

	public void addToQueue(String row) {
		writeToFile(row);
	}

	public void writeToFile(String output) {

		try {
			out.write(output + "\n");
		} catch (IOException e) {
			System.out.println("Probleem kirjutamisel: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public void closeStream() {
		try {
			out.close();
		} catch (IOException e) {
			System.out.println("Ei õnnestunud streami sulgeda: "
					+ e.getMessage());
			e.printStackTrace();
		}

	}

}
