package otter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import wordnet.WordNet;
import wordnet.WordNetResult;

public class OtterInputGenerator {

	BufferedWriter bufferedWriter = null;
	WordNet wn = new WordNet();

	public String generate() throws IOException {

		File file = generateFileName();
		openBuffers(file);
		writeInparams();
		writeRules();
		finalizeFile();
		return file.getName();
	}

	private void finalizeFile() throws IOException {
		bufferedWriter.write("\nend_of_list.\n");
		bufferedWriter.close();
	}

	private void writeRules() throws IOException {
//		ArrayList<String> post1 = getPost();
//		ArrayList<String> post2 = getPost();
		
		ArrayList<String> post1 = new ArrayList<>();
		post1.add("dog");
		
		ArrayList<String> post2 = new ArrayList<>();
		post2.add("cat");
		
		ArrayList<String> syno1 = getAllSynonyms(post1);
		ArrayList<String> syno2 = getAllSynonyms(post2);
		
		writePairs("synon", syno1, syno2);

	}

	private ArrayList<String> getAllSynonyms(ArrayList<String> post) {
		ArrayList<String> syns = new ArrayList<>();
		for (String p: post) {
			wn.getWord(p);
			WordNetResult wnr = wn.getResult();
			syns.addAll(wnr.getSenses().get(0).getSynonyms());
		}
		return syns;
	}

	private void writePairs(String string, ArrayList<String> post1,
			ArrayList<String> post2) throws IOException {
		for (String p1: post1) {
			for (String p2: post2) {
				if(!p1.equalsIgnoreCase(p2)) { 
					bufferedWriter.write(string + "(" + p1 + "," + p2 + ").\n");
				}
			}
		}
		
	}

	private void writeInparams() throws IOException {
		String line = "";
		String inParamPath = "config\\otterInParams.txt";

		BufferedReader br = null;

		br = new BufferedReader(new FileReader(inParamPath));
		line = br.readLine();

		while (line != null) {
			bufferedWriter.write(line + "\n");
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
