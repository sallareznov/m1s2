package ftp.command;

import ftp.FTPDatabase;
import ftp.FTPMessageSender;
import ftp.configuration.FTPClientConfiguration;

public class FTPUserCommand extends FTPMessageSender implements FTPCommand {
	
	public FTPUserCommand(FTPDatabase database) {
		super(database);
	}

	@Override
	public boolean accept(String command) {
		return command.equals("USER");
	}

	@Override
	public void execute(String argument,
			FTPClientConfiguration clientConfiguration) {
		clientConfiguration.setUsername(argument);
		sendCommandWithDefaultMessage(clientConfiguration.getConnection(), 331);
	}

}
