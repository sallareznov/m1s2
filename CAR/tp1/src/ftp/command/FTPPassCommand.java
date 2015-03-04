package ftp.command;

import java.net.Socket;

import ftp.FTPDatabase;
import ftp.FTPMessageSender;
import ftp.FTPRequest;
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
	public boolean accept(FTPRequest request) {
		final String command = request.getCommand();
		return command.equals("PASS");
	}
	
	@Override
	public boolean isValid(FTPRequest request) {
		return (request.getLength() == 2);
	}

	@Override
	public void execute(FTPRequest request,
			FTPClientConfiguration clientConfiguration) {
		if (!isValid(request)) {
			sendCommand(clientConfiguration.getCommandSocket(), 501);
			return;
		}
		final String username = clientConfiguration.getUsername();
		final Socket connection = clientConfiguration.getCommandSocket();
		final FTPDatabase database = getDatabase();
		if (clientConfiguration.getUsername() == null) {
			sendCommand(connection, 530);
		}
		if (request.getArgument().equals(database.getAccounts().get(username))) {
			sendCommand(connection, 230);
			clientConfiguration.setConnected(true);
		} else {
			sendCommand(connection, 332);
		}
	}

}
