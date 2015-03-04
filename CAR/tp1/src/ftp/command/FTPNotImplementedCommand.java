package ftp.command;

import ftp.FTPDatabase;
import ftp.FTPMessageSender;
import ftp.FTPRequest;
import ftp.configuration.FTPClientConfiguration;

/**
 * Class representing a non-implemented command
 */
public class FTPNotImplementedCommand extends FTPMessageSender implements
		FTPCommand {
	
	/**
	 * constructs a non-implemented command
	 * @param database the database
	 */
	public FTPNotImplementedCommand(FTPDatabase database) {
		super(database);
	}

	@Override
	public boolean accept(FTPRequest request) {
		return true;
	}
	
	@Override
	public boolean isValid(FTPRequest request) {
		return true;
	}

	@Override
	public void execute(FTPRequest request,
			FTPClientConfiguration clientConfiguration) {
		sendCommand(clientConfiguration.getCommandSocket(), 502);
	}

}
