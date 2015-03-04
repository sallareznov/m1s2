package ftp.command;

import ftp.FTPDatabase;
import ftp.FTPMessageSender;
import ftp.FTPRequest;
import ftp.configuration.FTPClientConfiguration;

/**
 * Class representing the USER command
 */
public class FTPUserCommand extends FTPMessageSender implements FTPCommand {

	/**
	 * constructs a USER command
	 * 
	 * @param database
	 *            the database
	 */
	public FTPUserCommand(FTPDatabase database) {
		super(database);
	}

	@Override
	public boolean accept(FTPRequest request) {
		final String command = request.getCommand();
		return command.equals("USER");
	}
	
	@Override
	public boolean isValid(FTPRequest request) {
		return (request.getLength() == 2);
	}

	@Override
	public void execute(FTPRequest request,
			FTPClientConfiguration clientConfiguration) {
		if (!isValid(request)) {
			sendCommand(clientConfiguration.getCommandSocket(), 501);
			return;
		}
		clientConfiguration.setUsername(request.getArgument());
		if (request.getArgument().equals("anonymous")) {
			sendCommand(clientConfiguration.getCommandSocket(),
					230);
			clientConfiguration.setConnected(true);
		} else {
			sendCommand(clientConfiguration.getCommandSocket(),
					331);
		}
	}

}
