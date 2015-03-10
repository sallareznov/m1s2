package ftp.shared;


import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class FTPLoggerSimpleFormatter extends Formatter {

	@Override
	public String format(LogRecord record) {
		return formatMessage(record) + "\n";
	}

}
