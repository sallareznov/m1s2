package ftp.command;

import java.io.File;
import java.io.IOException;

import ftp.FTPDatabase;
import ftp.FTPMessageSender;
import ftp.configuration.FTPClientConfiguration;

/**
 * Class representing the LIST command
 */
public class FTPListCommand extends FTPMessageSender implements FTPCommand {
	
	/**
	 * constructs a LIST command
	 * @param database the database
	 */
	public FTPListCommand(FTPDatabase database) {
		super(database);
	}

	@Override
	public boolean accept(String command) {
		return command.equals("LIST");
	}

	@Override
	public void execute(String argument,
			FTPClientConfiguration clientConfiguration) {
		if (!clientConfiguration.isConnected()) {
			sendCommandWithDefaultMessage(clientConfiguration.getConnection(), 530);
			return;
		}
		sendCommandWithDefaultMessage(clientConfiguration.getConnection(), 150);
		final String workingDirectoryPath = clientConfiguration
				.getWorkingDirectory();
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
		sendCommandWithDefaultMessage(clientConfiguration.getConnection(), 226);
	}

}
