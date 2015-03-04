package ftp.command;

import ftp.FTPDatabase;
import ftp.FTPMessageSender;
import ftp.FTPRequest;
import ftp.configuration.FTPClientConfiguration;

/**
 * Class representing the TYPE command
 */
public class FTPTypeCommand extends FTPMessageSender implements FTPCommand {
	
	/**
	 * constructs a TYPE command
	 * @param database the database
	 */
	public FTPTypeCommand(FTPDatabase database) {
		super(database);
	}

	@Override
	public boolean accept(FTPRequest request) {
		final String command = request.getCommand();
		return (command.equals("TYPE"));
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
		sendCommand(clientConfiguration.getCommandSocket(), 200, "TYPE");
	}

}
