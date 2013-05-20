package otter;

import java.io.File;

import shared.Executable;
import shared.ExecutableResult;
import shared.SystemCaller;

public class OtterCaller implements Executable {
	
	SystemCaller sysCall = new SystemCaller();
	
	public OtterCaller(String inFile, File outFile) {
		sysCall.execute(this, "otter < " + inFile + " > " + outFile.getName());
	}

	@Override
	public void setResult(ExecutableResult execRes) {
//		execRes.print();
		
	}
}
