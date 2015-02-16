package ftp.command;

import ftp.FTPDatabase;
import ftp.FTPMessageSender;
import ftp.configuration.FTPClientConfiguration;

public class FTPCwdCommand extends FTPMessageSender implements FTPCommand {
	
	public FTPCwdCommand(FTPDatabase database) {
		super(database);
	}

	@Override
	public boolean accept(String command) {
		return command.equals("CWD");
	}

	@Override
	public void execute(String argument,
			FTPClientConfiguration clientConfiguration) {
		// TODO Auto-generated method stub

	}

}
