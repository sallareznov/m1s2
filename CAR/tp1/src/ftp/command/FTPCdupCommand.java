package ftp.command;

import ftp.FTPDatabase;
import ftp.FTPMessageSender;
import ftp.FTPRequest;
import ftp.FailedCwdException;
import ftp.configuration.FTPClientConfiguration;

/**
 * Class representing a CDUP command
 */
public class FTPCdupCommand extends FTPMessageSender implements FTPCommand {

	/**
	 * constructs a CDUP command
	 * 
	 * @param database
	 *            the database
	 */
	public FTPCdupCommand(FTPDatabase database) {
		super(database);
	}

	@Override
	public boolean accept(FTPRequest request) {
		final String command = request.getCommand();
		return command.equals("CDUP");
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
		if (!clientConfiguration.isConnected()) {
			sendCommand(clientConfiguration.getCommandSocket(), 530);
			return;
		}
		try {
			clientConfiguration.goUp();
			sendCommand(clientConfiguration.getCommandSocket(), 200, "CDUP");
		} catch (FailedCwdException e) {
			sendCommand(clientConfiguration.getCommandSocket(), 550);
		}
	}

}
