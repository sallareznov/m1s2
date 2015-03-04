package ftp.command;

import java.io.FileNotFoundException;
import java.net.Socket;
import java.util.StringTokenizer;

import ftp.FTPDatabase;
import ftp.FTPMessageSender;
import ftp.FTPRequest;
import ftp.FailedCwdException;
import ftp.configuration.FTPClientConfiguration;

/**
 * Class representing the CWD command
 */
public class FTPCwdCommand extends FTPMessageSender implements FTPCommand {

	/**
	 * constructs a CWD-command
	 * 
	 * @param database
	 *            the database
	 */
	public FTPCwdCommand(FTPDatabase database) {
		super(database);
	}

	@Override
	public boolean accept(FTPRequest request) {
		final String command = request.getCommand();
		return command.equals("CWD");
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
		if (!clientConfiguration.isConnected()) {
			sendCommand(clientConfiguration.getCommandSocket(), 530);
			return;
		}
		final Socket connection = clientConfiguration.getCommandSocket();
		final StringTokenizer tokenizer = new StringTokenizer(
				request.getArgument(),
				clientConfiguration.getDirectorySeparator());
		try {
			while (tokenizer.hasMoreTokens()) {
				final String aDirectory = tokenizer.nextToken();
				if (!".".equals(aDirectory)) {
					if ("..".equals(aDirectory))
						clientConfiguration.goUp();
					else
						clientConfiguration.goDown(aDirectory);
				}
			}
			sendCommand(connection, 250);
		} catch (FileNotFoundException e) {
			sendCommand(clientConfiguration.getCommandSocket(), 550);
		} catch (FailedCwdException e) {
			sendCommand(clientConfiguration.getCommandSocket(), 550);
		}
	}
}
