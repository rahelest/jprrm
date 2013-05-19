package otter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class OtterInputGenerator {

	BufferedWriter bufferedWriter = null;

	public String generate() throws IOException {

		File file = generateFileName();
		openBuffers(file);
		writeInparams();
		writeRules();
		finalizeFile();
		return file.getName();
	}

	private void finalizeFile() throws IOException {
		bufferedWriter.close();
	}

	private void writeRules() {
		// TODO Auto-generated method stub

	}

	private void writeInparams() throws IOException {
		String line = "";
		String inParamPath = "config\\otterInParams.txt";

		BufferedReader br = null;

		br = new BufferedReader(new FileReader(inParamPath));
		line = br.readLine();

		while (line != null) {
			bufferedWriter.write(line);
			System.out.println(line);
			line = br.readLine();
		}

		br.close();

	}

	private void openBuffers(File file) {
		FileWriter writer = null;
		try {
			writer = new FileWriter(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		bufferedWriter = new BufferedWriter(writer);

	}

	private File generateFileName() throws IOException {
		Long unixTime = new Long(System.currentTimeMillis() / 1000L);
		String unixTimeString = unixTime.toString();
		String fileName = "OTTER_" + unixTimeString + ".in";
		
		System.out.println(fileName);

		File file = new File(fileName);
		if (!file.exists()) {
			file.createNewFile();
		}

		return file;
	}

}
