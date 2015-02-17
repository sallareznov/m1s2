package ftp.command;

import ftp.FTPDatabase;
import ftp.FTPMessageSender;
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
	public boolean accept(String command) {
		return true;
	}

	@Override
	public void execute(String argument,
			FTPClientConfiguration clientConfiguration) {
		sendCommandWithDefaultMessage(clientConfiguration.getConnection(), 502);
	}

}
