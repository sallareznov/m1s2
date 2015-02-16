package ftp.command;

import ftp.FTPDatabase;
import ftp.FTPMessageSender;
import ftp.configuration.FTPClientConfiguration;

public class FTPTypeCommand extends FTPMessageSender implements FTPCommand {
	
	public FTPTypeCommand(FTPDatabase database) {
		super(database);
	}

	@Override
	public boolean accept(String command) {
		return (command.equals("TYPE"));
	}

	@Override
	public void execute(String argument,
			FTPClientConfiguration clientConfiguration) {
		sendCommandWithFormattedMessage(clientConfiguration.getConnection(), 200, "TYPE");
	}

}
