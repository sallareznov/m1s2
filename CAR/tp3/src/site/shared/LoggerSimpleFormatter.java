package site.shared;


import java.util.logging.Formatter;
import java.util.logging.LogRecord;

/**
 * Simple formatter (removes the syntactic sugar of loggers)
 */
public class LoggerSimpleFormatter extends Formatter {

	@Override
	public String format(LogRecord record) {
		return formatMessage(record) + "\n";
	}

}
