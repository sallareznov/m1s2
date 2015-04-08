package coloration.logger;

import java.util.logging.ConsoleHandler;
import java.util.logging.Logger;

public class LoggerFactory {
	
	private LoggerFactory() {
		// Utility class : private constructor to hide the implicit 'public' declaration of the class
	}
	
	public static Logger getLogger(Class<?> classObject) {
		final Logger logger = Logger.getLogger(classObject.getName());
		logger.setUseParentHandlers(false);
		final ConsoleHandler consoleHandler = new ConsoleHandler();
		consoleHandler.setFormatter(new SimpleFormatter());
		logger.addHandler(consoleHandler);
		return logger;
	}

}
