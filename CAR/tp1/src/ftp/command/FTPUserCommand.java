package ftp.command;

import ftp.FTPDatabase;
import ftp.FTPMessageSender;
import ftp.configuration.FTPClientConfiguration;

/**
 * Class representing the USER command
 */
public class FTPUserCommand extends FTPMessageSender implements FTPCommand {

	/**
	 * constructs a USER command
	 * 
	 * @param database
	 *            the database
	 */
	public FTPUserCommand(FTPDatabase database) {
		super(database);
	}

	@Override
	public boolean accept(String command) {
		return command.equals("USER");
	}

	@Override
	public void execute(String argument,
			FTPClientConfiguration clientConfiguration) {
		clientConfiguration.setUsername(argument);
		if (argument.equals("anonymous")) {
			sendCommand(clientConfiguration.getConnection(),
					230);
			clientConfiguration.setConnected(true);
		} else {
			sendCommand(clientConfiguration.getConnection(),
					331);
		}
	}

}
