package ftp.command;

import java.io.File;

import ftp.FTPMessageSender;
import ftp.configuration.FTPClientConfiguration;

public class FTPListCommand extends FTPMessageSender implements FTPCommand {

	@Override
	public boolean accept(String[] requestTokens) {
		return requestTokens[0].equals("LIST");
	}

	@Override
	public void execute(String[] requestTokens,
			FTPClientConfiguration clientConfiguration) {
		final String workingDirectoryPath = clientConfiguration.getWorkingDirectory();
		final File workingDirectory = new File(workingDirectoryPath);
		final String[] directoryListing = workingDirectory.list();
		for (String entry : directoryListing) {
			System.out.println(entry);
		}
	}

}
