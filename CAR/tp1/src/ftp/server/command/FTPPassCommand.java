package ftp.server.command;

import java.io.IOException;
import java.net.Socket;

import ftp.shared.FTPClientConfiguration;
import ftp.shared.FTPDatabase;
import ftp.shared.FTPRequest;

/**
 * Class representing a PASS command
 */
public class FTPPassCommand extends FTPBasicCommand {

	/**
	 * constructs a PASS command
	 * @param database the database
	 */
	public FTPPassCommand(FTPDatabase database) {
		super(database, "PASS");
	}
	
	@Override
	public boolean isValid(FTPRequest request) {
		return request.getLength() == 2;
	}

	@Override
	public void executeValidCommand(FTPRequest request,
			FTPClientConfiguration clientConfiguration) throws IOException {
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
