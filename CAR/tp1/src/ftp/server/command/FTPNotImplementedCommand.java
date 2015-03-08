package ftp.server.command;

import java.io.IOException;

import ftp.shared.FTPClientConfiguration;
import ftp.shared.FTPDatabase;
import ftp.shared.FTPMessageSender;
import ftp.shared.FTPRequest;

/**
 * Class representing a non-implemented command. This command
 * will be executed if the server doesn't accept the client's request
 */
public class FTPNotImplementedCommand extends FTPMessageSender implements
		FTPCommand {
	
	/**
	 * constructs a non-implemented command
	 * @param database the database
	 */
	public FTPNotImplementedCommand(FTPDatabase database) {
		super(database);
	}

	@Override
	public boolean accept(FTPRequest request) {
		return true;
	}
	
	@Override
	public boolean isValid(FTPRequest request) {
		return true;
	}

	@Override
	public void execute(FTPRequest request,
			FTPClientConfiguration clientConfiguration) throws IOException {
		sendCommand(clientConfiguration.getCommandSocket(), 502);
	}

}
