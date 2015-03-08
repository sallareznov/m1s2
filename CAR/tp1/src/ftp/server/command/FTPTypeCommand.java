package ftp.server.command;

import java.io.IOException;

import ftp.shared.FTPClientConfiguration;
import ftp.shared.FTPDatabase;
import ftp.shared.FTPRequest;

/**
 * Class representing the TYPE command
 */
public class FTPTypeCommand extends FTPBasicCommand {
	
	/**
	 * constructs a TYPE command
	 * @param database the database
	 */
	public FTPTypeCommand(FTPDatabase database) {
		super(database, "TYPE");
	}
	
	@Override
	public boolean isValid(FTPRequest request) {
		return request.getLength() == 1;
	}

	@Override
	public void executeValidCommand(FTPRequest request,
			FTPClientConfiguration clientConfiguration) throws IOException {
		sendCommand(clientConfiguration.getCommandSocket(), 200, getName());
	}

}
