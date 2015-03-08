package ftp.shared;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

/**
 * Class representing a LoggerFactory 
 */
public class FTPLoggerFactory {
	
	private FTPLoggerFactory() {
		// Utility class = private constructor to hide the implicit public one.
	}
	
	/**
	 * creates a logger
	 * @param clazz the class object
	 * @return the created logger
	 */
	public static Logger create(Class<?> clazz) {
		final Logger logger = Logger.getLogger(clazz);
		final ConsoleAppender consoleAppender = new ConsoleAppender();
		consoleAppender.setLayout(new PatternLayout("%m%n"));
		consoleAppender.activateOptions();
		logger.addAppender(consoleAppender);
		return logger;
	}

}
