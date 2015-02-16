package ftp.command;

import ftp.FTPDatabase;
import ftp.FTPMessageSender;
import ftp.configuration.FTPClientConfiguration;

public class FTPPwdCommand extends FTPMessageSender implements FTPCommand {
	
	public FTPPwdCommand(FTPDatabase database) {
		super(database);
	}

	@Override
	public boolean accept(String command) {
		return command.equals("PWD");
	}

	@Override
	public void execute(String argument,
			FTPClientConfiguration clientConfiguration) {
		final String workingDirectory = clientConfiguration
				.getWorkingDirectory();
		sendCommandWithFormattedMessage(clientConfiguration.getConnection(),
				257, workingDirectory);
	}

}
