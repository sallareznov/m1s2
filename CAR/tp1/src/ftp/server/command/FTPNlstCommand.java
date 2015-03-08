package ftp.server.command;

import java.io.File;
import java.io.IOException;

import ftp.shared.FTPClientConfiguration;
import ftp.shared.FTPDatabase;
import ftp.shared.FTPRequest;

/**
 * Class representing the NLST command
 */
public class FTPNlstCommand extends FTPConnectionNeededCommand {

	/**
	 * constructs a NLST command
	 * 
	 * @param database
	 *            the database
	 */
	public FTPNlstCommand(FTPDatabase database) {
		super(database, "NLST");
	}

	@Override
	public boolean isValid(FTPRequest request) {
		return request.getLength() <= 2;
	}

	private String buildList(String workingDirectoryPath,
			FTPClientConfiguration clientConfiguration) {
		final File workingDirectory = new File(workingDirectoryPath);
		final File[] directoryListing = workingDirectory.listFiles();
		final StringBuilder messageBuilder = new StringBuilder();
		for (final File entry : directoryListing) {
			if (!entry.isHidden()) {
				if (entry.isDirectory())
					messageBuilder.append(entry.getName()
							+ clientConfiguration.getDirectorySeparator()
							+ "\n");
				else
					messageBuilder.append(entry.getName() + "\n");
			}
		}
		return messageBuilder.toString();
	}

	@Override
	public void executeConnectionNeededCommand(FTPRequest request,
			FTPClientConfiguration clientConfiguration) throws IOException {
		sendCommand(clientConfiguration.getCommandSocket(), 150);
		final String workingDirectoryPath = (request.getArgument() == null) ? clientConfiguration
				.getWorkingDirectory() : request.getArgument();
		final String list = buildList(workingDirectoryPath, clientConfiguration);
		sendData(clientConfiguration.getDataSocket(), list);
		try {
			clientConfiguration.closeDataSocket();
		} catch (IOException e) {
			sendCommand(clientConfiguration.getCommandSocket(), 500);
		}
		sendCommand(clientConfiguration.getCommandSocket(), 226);
	}

}
