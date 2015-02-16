package ftp.command;

import ftp.FTPDatabase;
import ftp.FTPMessageSender;
import ftp.configuration.FTPClientConfiguration;

public class FTPRetrCommand extends FTPMessageSender implements FTPCommand {
	
	public FTPRetrCommand(FTPDatabase database) {
		super(database);
	}

	@Override
	public boolean accept(String command) {
		return command.equals("RETR");
	}

	@Override
	public void execute(String argument,
			FTPClientConfiguration clientConfiguration) {
		// TODO Auto-generated method stub

	}

}
