package ftp;

import java.io.IOException;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.HTMLLayout;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;

public abstract class FTPLoggable {

	private Logger _logger;

	public FTPLoggable() {
		try {
			_logger = Logger.getLogger(getClass());
			final ConsoleAppender console = new ConsoleAppender();
			console.setLayout(new SimpleLayout());
			console.activateOptions();
			_logger.addAppender(console);
			_logger.addAppender(new FileAppender(new HTMLLayout(), "test.html"));
		} catch (IOException e) {
		}
	}

	public Logger getLogger() {
		return _logger;
	}
	
	public void debug(String message) {
		_logger.debug(message);
	}
	
	public void error(String message) {
		_logger.error(message);
	}
	
	public void fatal(String message) {
		_logger.fatal(message);
	}
	
	public void info(String message) {
		_logger.info(message);
	}
	
}
