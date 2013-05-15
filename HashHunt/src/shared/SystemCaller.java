package shared;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class SystemCaller {

	Runtime runTime = null;
	Process p = null;

	public SystemCaller() {
		runTime = Runtime.getRuntime();
	}

	public static void main(String[] args) {

		try {
			Process p = Runtime.getRuntime().exec("cmd /c dir");
			p.waitFor();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					p.getInputStream()));
			String line = reader.readLine();
			while (line != null) {
				System.out.println(line);
				line = reader.readLine();
			}

		} catch (IOException e1) {
		} catch (InterruptedException e2) {
		}
	}

	public String execute(String command) {
		String line = "";
		System.out.println("COMMAND: " + command);
		try {
			p = runTime.exec("cmd /c " + command);
//			p.waitFor();
			StreamThread inputStream = new StreamThread(p.getInputStream(), "INPUT");
			StreamThread errorStream = new StreamThread(p.getErrorStream(), "ERROR");
			
			inputStream.start();
			errorStream.start();
			// System.out.println("ERROR: " + getResponse(p.getErrorStream()));
//			return "INPUT: " + getResponse(p.getInputStream());
//		} catch (InterruptedException e) {
//			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public String getResponse(InputStream iS) {
		String line = "";
		String response = "";
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(iS));
			line = reader.readLine();
			while (line != null) {
				response += line + "\n";
				line = reader.readLine();

			}
		} catch (IOException e1) {
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return response;
	}

	public class StreamThread extends Thread {

		InputStream inpStr;
		String strType;

		public StreamThread(InputStream inpStr, String strType) {
			this.inpStr = inpStr;
			this.strType = strType;
		}

		public void run() {
			try {
				InputStreamReader inpStrd = new InputStreamReader(inpStr);
				BufferedReader buffRd = new BufferedReader(inpStrd);
				String line = null;
				while ((line = buffRd.readLine()) != null) {
					System.out.println(strType + " —> " + line);
				}
				buffRd.close();

			} catch (Exception e) {
				System.out.println(e);
			}

		}

	}

}