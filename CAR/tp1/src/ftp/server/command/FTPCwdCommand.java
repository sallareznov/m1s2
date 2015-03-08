package ftp.server.command;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.Socket;
import java.util.StringTokenizer;

import ftp.server.util.FailedCwdException;
import ftp.shared.FTPClientConfiguration;
import ftp.shared.FTPDatabase;
import ftp.shared.FTPRequest;

/**
 * Class representing the CWD command
 */
public class FTPCwdCommand extends FTPConnectionNeededCommand {

	/**
	 * constructs a CWD-command
	 * 
	 * @param database
	 *            the database
	 */
	public FTPCwdCommand(FTPDatabase database) {
		super(database, "CWD");
	}

	@Override
	public boolean isValid(FTPRequest request) {
		return request.getLength() == 2;
	}

	private void handleDirectory(String directory,
			FTPClientConfiguration clientConfiguration)
			throws FailedCwdException, IOException {
		try {
			if (!".".equals(directory)) {
				if ("..".equals(directory))
					clientConfiguration.goUp();
				else
					clientConfiguration.goDown(directory);
			}
		} catch (FileNotFoundException e) {
			sendCommand(clientConfiguration.getCommandSocket(), 550);
		}
	}

	@Override
	public void executeConnectionNeededCommand(FTPRequest request,
			FTPClientConfiguration clientConfiguration) throws IOException {
		final Socket connection = clientConfiguration.getCommandSocket();
		final StringTokenizer tokenizer = new StringTokenizer(
				request.getArgument(),
				clientConfiguration.getDirectorySeparator());
		try {
			while (tokenizer.hasMoreTokens()) {
				final String aDirectory = tokenizer.nextToken();
				handleDirectory(aDirectory, clientConfiguration);
			}
			sendCommand(connection, 250);
		} catch (FailedCwdException e) {
			sendCommand(clientConfiguration.getCommandSocket(), 550);
		}
	}
}
