package ftp.shared;

import java.util.logging.ConsoleHandler;
import java.util.logging.Logger;

public class FTPLoggerFactory {
	
	private FTPLoggerFactory() {
		// Utility class : private constructor to hide the implicit 'public' declaration of the class
	}
	
	public static Logger create(Class<?> classObject) {
		final Logger logger = Logger.getLogger(classObject.getName());
		logger.setUseParentHandlers(false);
		final ConsoleHandler consoleHandler = new ConsoleHandler();
		consoleHandler.setFormatter(new FTPLoggerSimpleFormatter());
		logger.addHandler(consoleHandler);
		return logger;
	}

}
