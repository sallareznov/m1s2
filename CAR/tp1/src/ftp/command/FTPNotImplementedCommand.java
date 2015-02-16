package ftp.command;

import ftp.FTPDatabase;
import ftp.FTPMessageSender;
import ftp.configuration.FTPClientConfiguration;

public class FTPNotImplementedCommand extends FTPMessageSender implements
		FTPCommand {
	
	public FTPNotImplementedCommand(FTPDatabase database) {
		super(database);
	}

	@Override
	public boolean accept(String command) {
		return true;
	}

	@Override
	public void execute(String argument,
			FTPClientConfiguration clientConfiguration) {
		sendCommandWithDefaultMessage(clientConfiguration.getConnection(), 502);

	}

}
