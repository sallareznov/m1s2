package ftp.command;

import java.net.Socket;

import ftp.FTPDatabase;
import ftp.FTPMessageSender;
import ftp.configuration.FTPClientConfiguration;

/**
 * Class representing a PASS command
 */
public class FTPPassCommand extends FTPMessageSender implements FTPCommand {

	/**
	 * constructs a PASS command
	 * @param database the database
	 */
	public FTPPassCommand(FTPDatabase database) {
		super(database);
	}

	@Override
	public boolean accept(String command) {
		return command.equals("PASS");
	}

	@Override
	public void execute(String argument,
			FTPClientConfiguration clientConfiguration) {
		final String username = clientConfiguration.getUsername();
		final Socket connection = clientConfiguration.getConnection();
		final FTPDatabase database = getDatabase();
		if (argument.equals(database.getAccounts().get(username))) {
			sendCommandWithDefaultMessage(connection, 230);
			clientConfiguration.setConnected(true);
			//sendCommandWithDefaultMessage(connection, 225);
		} else {
			sendCommandWithDefaultMessage(connection, 430);
			sendCommandWithDefaultMessage(connection, 220);
		}
	}

}
