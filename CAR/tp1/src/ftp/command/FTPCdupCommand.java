package ftp.command;

import ftp.FTPDatabase;
import ftp.FTPMessageSender;
import ftp.configuration.FTPClientConfiguration;

/**
 * Class representing a CDUP command
 */
public class FTPCdupCommand extends FTPMessageSender implements FTPCommand {
	
	/**
	 * constructs a CDUP command
	 * @param database the database
	 */
	public FTPCdupCommand(FTPDatabase database) {
		super(database);
	}

	@Override
	public boolean accept(String command) {
		return command.equals("CDUP");
	}

	@Override
	public void execute(String argument,
			FTPClientConfiguration clientConfiguration) {
		if (!clientConfiguration.isConnected()) {
			sendCommandWithDefaultMessage(clientConfiguration.getConnection(), 530);
			return;
		}
		new FTPCwdCommand(getDatabase()).execute("..", clientConfiguration);
	}

}
