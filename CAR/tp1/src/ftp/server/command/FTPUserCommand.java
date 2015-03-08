package ftp.server.command;

import java.io.IOException;

import ftp.shared.FTPClientConfiguration;
import ftp.shared.FTPDatabase;
import ftp.shared.FTPRequest;

/**
 * Class representing the USER command
 */
public class FTPUserCommand extends FTPBasicCommand {

	/**
	 * constructs a USER command
	 * 
	 * @param database
	 *            the database
	 */
	public FTPUserCommand(FTPDatabase database) {
		super(database, "USER");
	}
	
	@Override
	public boolean isValid(FTPRequest request) {
		return request.getLength() == 2;
	}

	@Override
	public void executeValidCommand(FTPRequest request,
			FTPClientConfiguration clientConfiguration) throws IOException {
		clientConfiguration.setUsername(request.getArgument());
		if ("anonymous".equals(request.getArgument())) {
			sendCommand(clientConfiguration.getCommandSocket(),
					230);
			clientConfiguration.setConnected(true);
		} else {
			sendCommand(clientConfiguration.getCommandSocket(),
					331);
		}
	}

}
