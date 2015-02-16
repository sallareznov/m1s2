package ftp.command;

import ftp.FTPDatabase;
import ftp.FTPMessageSender;
import ftp.configuration.FTPClientConfiguration;

public class FTPCdupCommand extends FTPMessageSender implements FTPCommand {
	
	public FTPCdupCommand(FTPDatabase database) {
		super(database);
	}

	@Override
	public boolean accept(String command) {
		return command.equals("CDUP");
	}

	@Override
	public void execute(String argument,
			FTPClientConfiguration clientConfiguration) {
		new FTPCwdCommand(getDatabase()).execute("..", clientConfiguration);
	}

}
