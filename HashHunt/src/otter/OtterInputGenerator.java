package otter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import flickr.FlickrQueue;

import shared.SocialStreamQueue;
import twitter.TwitterQueue;
import wordnet.WordNet;
import wordnet.WordNetResult;

public class OtterInputGenerator extends Thread {

	BufferedWriter bufferedWriter = null;
	WordNet wn = new WordNet();
	SocialStreamQueue fq;
	SocialStreamQueue tq;

	public OtterInputGenerator(FlickrQueue fq, TwitterQueue tq) {
		this.fq = fq;
		this.tq = tq;
		this.start();
	}

	public void run() {
		while (true) {
			String fileName = generate();
			File outFile = generateFileName("out");
			new OtterCaller(fileName, outFile);

		}
	}

	private String generate() {

		File file;
		try {
			file = generateFileName("in");

			openBuffers(file);
			writeInparams();
			writeRules();
			finalizeFile();
			return file.getName();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private void finalizeFile() throws IOException {
		bufferedWriter.write("\nend_of_list.\n");
		bufferedWriter.close();
	}

	private void writeRules() throws IOException {
		// ArrayList<String> post1 = getPost();
		// ArrayList<String> post2 = getPost();

		ArrayList<String> post1 = new ArrayList<>();
		post1.add("dog");

		ArrayList<String> post2 = new ArrayList<>();
		post2.add("cat");

		WordNetResult wnr1 = getSynonymsAndHypers(post1.get(0));

		ArrayList<String> syno1 = getSynos(wnr1);

		writeSynonPairs(syno1);

		ArrayList<String> hyper1 = getHypers(wnr1);

		writeHyperPairs(post1.get(0), hyper1);

	}

	private void writeHyperPairs(String word, ArrayList<String> hyper1)
			throws IOException {
		for (String h : hyper1) {
			bufferedWriter.write("hyper(" + word + "," + h + ").\n");
		}

	}

	private ArrayList<String> getHypers(WordNetResult wnr) {
		return wnr.getSenses().get(0).getAllHypernyms();
	}

	private ArrayList<String> getSynos(WordNetResult wnr) {
		return wnr.getSenses().get(0).getSynonyms();
	}

	private WordNetResult getSynonymsAndHypers(String word) {
		wn.getWord(word);
		WordNetResult wnr = wn.getResult();
		return wnr;
	}

	private void writeSynonPairs(ArrayList<String> post1) throws IOException {
		for (String p1 : post1) {
			for (String p2 : post1) {
				if (!p1.equalsIgnoreCase(p2)) {
					bufferedWriter.write("synon(" + p1 + "," + p2 + ").\n");
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

	private File generateFileName(String dir)  {
		Long unixTime = new Long(System.currentTimeMillis() / 1000L);
		String unixTimeString = unixTime.toString();
		String fileName = "OTTER\\OTTER_" + unixTimeString + "." + dir;

		System.out.println(fileName);

		File file = new File(fileName);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return file;
	}

}
