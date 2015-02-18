package ftp.command;

import ftp.FTPDatabase;
import ftp.FTPMessageSender;
import ftp.configuration.FTPClientConfiguration;

/**
 * Class representing the TYPE command
 */
public class FTPTypeCommand extends FTPMessageSender implements FTPCommand {
	
	/**
	 * constructs a TYPE command
	 * @param database the database
	 */
	public FTPTypeCommand(FTPDatabase database) {
		super(database);
	}

	@Override
	public boolean accept(String command) {
		return (command.equals("TYPE"));
	}

	@Override
	public void execute(String argument,
			FTPClientConfiguration clientConfiguration) {
		sendCommandWithFormattedMessage(clientConfiguration.getConnection(), 200, "TYPE");
	}

}
