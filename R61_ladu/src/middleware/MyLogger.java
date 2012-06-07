package middleware;

import org.apache.log4j.Logger;

public class MyLogger  {

	static Logger logger = Logger.getLogger(MyLogger.class);

	public static void error(String message) {
		logger.error(message);
	}
	
	public static void info(String message) {
		logger.info(message);
	}
}