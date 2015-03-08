package ftp.server.command;

import java.io.IOException;

import ftp.shared.FTPClientConfiguration;
import ftp.shared.FTPDatabase;
import ftp.shared.FTPRequest;

/**
 * Class representing the QUIT command
 */
public class FTPQuitCommand extends FTPBasicCommand {
	
	/**
	 * constructs a QUIT command
	 * @param database the database
	 */
	public FTPQuitCommand(FTPDatabase database) {
		super(database, "QUIT");
	}
	
	@Override
	public boolean isValid(FTPRequest request) {
		return request.getLength() == 1;
	}

	@Override
	public void executeValidCommand(FTPRequest request,
			FTPClientConfiguration clientConfiguration) throws IOException {
		sendCommand(clientConfiguration.getCommandSocket(), 221);
		clientConfiguration.setConnected(false);
	}

}
