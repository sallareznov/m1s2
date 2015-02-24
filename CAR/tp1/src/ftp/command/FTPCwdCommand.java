package ftp.command;

import java.io.FileNotFoundException;
import java.net.Socket;
import java.util.StringTokenizer;

import ftp.FTPDatabase;
import ftp.FTPMessageSender;
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
	public boolean accept(String command) {
		return command.equals("CWD");
	}

	@Override
	public void execute(String argument,
			FTPClientConfiguration clientConfiguration) {
		if (!clientConfiguration.isConnected()) {
			sendCommand(clientConfiguration.getCommandSocket(), 530);
			return;
		}
		final Socket connection = clientConfiguration.getCommandSocket();
		final StringTokenizer tokenizer = new StringTokenizer(argument,
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
		} catch (FileNotFoundException e) {
			sendCommand(clientConfiguration.getCommandSocket(), 550);
		} catch (FailedCwdException e) {
			sendCommand(clientConfiguration.getCommandSocket(), 550);
		}
		sendCommand(connection, 250);
	}
}
