package ftp.command;

import java.io.File;
import java.io.IOException;

import ftp.FTPDatabase;
import ftp.FTPMessageSender;
import ftp.FTPRequest;
import ftp.configuration.FTPClientConfiguration;

/**
 * Class representing the LIST command
 */
public class FTPNlstCommand extends FTPMessageSender implements FTPCommand {
	
	/**
	 * constructs a LIST command
	 * @param database the database
	 */
	public FTPNlstCommand(FTPDatabase database) {
		super(database);
	}

	@Override
	public boolean accept(FTPRequest request) {
		final String command = request.getCommand();
		return command.equals("NLST");
	}
	
	@Override
	public boolean isValid(FTPRequest request) {
		return (request.getLength() <= 2);
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
		sendCommand(clientConfiguration.getCommandSocket(), 150);
		final String workingDirectoryPath = (request.getArgument() == null) ? clientConfiguration
				.getWorkingDirectory() : request.getArgument();
		final File workingDirectory = new File(workingDirectoryPath);
		final File[] directoryListing = workingDirectory.listFiles();
		final StringBuilder messageBuilder = new StringBuilder();
		for (final File entry : directoryListing) {
			if (!entry.isHidden()) {
				if (entry.isDirectory())
					messageBuilder.append(entry.getName()
							+ clientConfiguration.getDirectorySeparator() + "\n");
				else
					messageBuilder.append(entry.getName() + "\n");
			}
		}
		sendData(clientConfiguration.getDataSocket(), messageBuilder.toString());
		try {
			clientConfiguration.closeDataSocket();
		} catch (IOException e) {
			System.out.println("ERROR while closing data socket");
		}
		sendCommand(clientConfiguration.getCommandSocket(), 226);
	}

}
