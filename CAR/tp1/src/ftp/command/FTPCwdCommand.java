package ftp.command;

import ftp.FTPDatabase;
import ftp.FTPMessageSender;
import ftp.configuration.FTPClientConfiguration;

/**
 * Class representing the CWD command 
 */
public class FTPCwdCommand extends FTPMessageSender implements FTPCommand {

	/**
	 * constructs a CWD-command
	 * @param database the database
	 */
	public FTPCwdCommand(FTPDatabase database) {
		super(database);
	}

	@Override
	public boolean accept(String command) {
		return command.equals("CWD");
	}

	@Override
	public void execute(String argument,
			FTPClientConfiguration clientConfiguration) {
		final String newDirectory = clientConfiguration.getWorkingDirectory() + clientConfiguration.getDirectorySeparator() + argument;
		clientConfiguration.setWorkingDirectory(newDirectory);
		sendCommandWithDefaultMessage(clientConfiguration.getConnection(), 250);

	}

}
