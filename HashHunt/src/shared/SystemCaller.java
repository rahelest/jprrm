package shared;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import wordnet.WordNetResultProcessor;

public class SystemCaller {

	Runtime runTime = null;
	Process p = null;

	public SystemCaller() {
		runTime = Runtime.getRuntime();
	}

	public void execute(String command) {
		System.out.println("COMMAND: " + command);
		try {
			p = runTime.exec("cmd /c " + command);
			
			new StreamThread(p.getInputStream(), "INPUT");
			new StreamThread(p.getErrorStream(), "ERROR");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public class StreamThread extends Thread {

		InputStream inpStr;
		String strType;

		public StreamThread(InputStream inpStr, String strType) {
			this.inpStr = inpStr;
			this.strType = strType;
			this.start();
		}

		public void run() {
			try {
				InputStreamReader inpStrd = new InputStreamReader(inpStr);
				BufferedReader buffRd = new BufferedReader(inpStrd);
				String line = null;
				String result = "";
				while ((line = buffRd.readLine()) != null) {
					result += line + "\n";
				}
				if (strType == "INPUT") {
					new WordNetResultProcessor(5).addHypernymTree(result);
				} else {
					System.out.print(result.length() > 0 ? "ERROR -> " + result + "\n" : "");
				}
				buffRd.close();

			} catch (Exception e) {
				System.out.println(e);
			}

		}

	}

}