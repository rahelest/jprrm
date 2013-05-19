package otter;

import java.io.IOException;

import shared.SystemCaller;

public class OtterCaller {
	
	SystemCaller sysCall = new SystemCaller();
	
	public OtterCaller(String inFile) {
		sysCall.execute("otter < " + inFile);
	}

	
	public static void main(String[] args) throws IOException {
		OtterInputGenerator oIG = new OtterInputGenerator();
		String inFile = oIG.generate();
		OtterCaller otterCaller = new OtterCaller(inFile);
	}
}
