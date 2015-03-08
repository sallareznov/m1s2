package ftp.server.command;

import java.io.IOException;

import ftp.shared.FTPClientConfiguration;
import ftp.shared.FTPDatabase;
import ftp.shared.FTPRequest;

/**
 * Class representing the PWD command
 */
public class FTPPwdCommand extends FTPConnectionNeededCommand {
	
	/**
	 * constructs a PWD command
	 * @param database the database
	 */
	public FTPPwdCommand(FTPDatabase database) {
		super(database, "PWD");
	}
	
	@Override
	public boolean isValid(FTPRequest request) {
		return request.getLength() == 1;
	}

	@Override
	public void executeConnectionNeededCommand(FTPRequest request,
			FTPClientConfiguration clientConfiguration) throws IOException {
		if (!isValid(request)) {
			sendCommand(clientConfiguration.getCommandSocket(), 501);
			return;
		}
		final String workingDirectory = clientConfiguration
				.getWorkingDirectory();
		sendCommand(clientConfiguration.getCommandSocket(),
				257, workingDirectory);
	}

}
