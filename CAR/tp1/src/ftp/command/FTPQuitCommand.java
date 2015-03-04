package ftp.command;

import ftp.FTPDatabase;
import ftp.FTPMessageSender;
import ftp.FTPRequest;
import ftp.configuration.FTPClientConfiguration;

/**
 * Class representing the QUIT command
 */
public class FTPQuitCommand extends FTPMessageSender implements FTPCommand {
	
	/**
	 * constructs a QUIT command
	 * @param database the database
	 */
	public FTPQuitCommand(FTPDatabase database) {
		super(database);
	}

	@Override
	public boolean accept(FTPRequest request) {
		final String command = request.getCommand();
		return command.equals("QUIT");
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
		sendCommand(clientConfiguration.getCommandSocket(), 221);
		clientConfiguration.setConnected(false);
	}

}
