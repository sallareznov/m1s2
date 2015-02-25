package ftp.command;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFileAttributes;
import java.nio.file.attribute.PosixFilePermissions;
import java.text.SimpleDateFormat;
import java.util.Date;

import ftp.FTPDatabase;
import ftp.FTPMessageSender;
import ftp.configuration.FTPClientConfiguration;

/**
 * Class representing the LIST command
 */
public class FTPListCommand extends FTPMessageSender implements FTPCommand {

	/**
	 * constructs a LIST command
	 * 
	 * @param database
	 *            the database
	 */
	public FTPListCommand(FTPDatabase database) {
		super(database);
	}

	@Override
	public boolean accept(String command) {
		return command.equals("LIST");
	}

	private String buildListItem(File entry) {
		try {
			PosixFileAttributes attr = Files.readAttributes(
					Paths.get(entry.getAbsolutePath()),
					PosixFileAttributes.class);
			char dir = '-';
			if (entry.isDirectory())
				dir = 'd';
			if (Files.isSymbolicLink(entry.toPath()))
				dir = 'l';
			String chmod = PosixFilePermissions.toString(Files
					.getPosixFilePermissions(entry.toPath()));
			String owner = attr.owner().getName();
			String group = attr.group().getName();
			long size = entry.length();
			String lastModif = new SimpleDateFormat("MMM dd HH:mm")
					.format(new Date(entry.lastModified()));
			String filename = entry.getName();

			return String.format("%c%s %s %s %6d %s %s", dir, chmod,
					owner, group, size, lastModif, filename);
		} catch (IOException e) {
			System.err.println("ERROR while getting POSIX attributes");
			return null;
		}
	}

	@Override
	public void execute(String argument,
			FTPClientConfiguration clientConfiguration) {
		if (!clientConfiguration.isConnected()) {
			sendCommand(clientConfiguration.getCommandSocket(), 530);
			return;
		}
		sendCommand(clientConfiguration.getCommandSocket(), 150);
		final String workingDirectoryPath = clientConfiguration
				.getWorkingDirectory();
		final File workingDirectory = new File(workingDirectoryPath);
		final File[] directoryListing = workingDirectory.listFiles();
		final StringBuilder messageBuilder = new StringBuilder();
		for (final File entry : directoryListing) {
			if (!entry.isHidden())
				messageBuilder.append(buildListItem(entry) + "\n");
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
