package ftp.command;

import ftp.FTPDatabase;
import ftp.FTPMessageSender;
import ftp.configuration.FTPClientConfiguration;

public class FTPQuitCommand extends FTPMessageSender implements FTPCommand {
	
	public FTPQuitCommand(FTPDatabase database) {
		super(database);
	}

	@Override
	public boolean accept(String command) {
		return command.equals("QUIT");
	}

	@Override
	public void execute(String argument,
			FTPClientConfiguration clientConfiguration) {
		sendCommandWithDefaultMessage(clientConfiguration.getConnection(), 221);
	}

}
