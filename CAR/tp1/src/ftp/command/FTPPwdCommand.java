package ftp.command;

import ftp.FTPDatabase;
import ftp.FTPMessageSender;
import ftp.FTPRequest;
import ftp.configuration.FTPClientConfiguration;

/**
 * Class representing the PWD command
 */
public class FTPPwdCommand extends FTPMessageSender implements FTPCommand {
	
	/**
	 * constructs a PWD command
	 * @param database the database
	 */
	public FTPPwdCommand(FTPDatabase database) {
		super(database);
	}

	@Override
	public boolean accept(FTPRequest request) {
		final String command = request.getCommand();
		return command.equals("PWD");
	}
	
	@Override
	public boolean isValid(FTPRequest request) {
		return (request.getLength() == 1);
	}

	@Override
	public void execute(FTPRequest request,
			FTPClientConfiguration clientConfiguration) {
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
