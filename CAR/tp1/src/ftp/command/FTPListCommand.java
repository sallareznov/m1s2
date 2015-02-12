package ftp.command;

import java.io.File;

import ftp.FTPMessageSender;
import ftp.configuration.FTPClientConfiguration;

public class FTPListCommand extends FTPMessageSender implements FTPCommand {

    @Override
    public boolean accept(String command) {
	return command.equals("LIST");
    }

    @Override
    public void execute(String argument, FTPClientConfiguration clientConfiguration) {
	final String workingDirectoryPath = clientConfiguration.getWorkingDirectory();
	final File workingDirectory = new File(workingDirectoryPath);
	final String[] directoryListing = workingDirectory.list();
	for (final String entry : directoryListing) {
	    System.out.println(entry);
	}
    }

}
