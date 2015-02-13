package ftp.command;

import ftp.FTPMessageSender;
import ftp.configuration.FTPClientConfiguration;

public class FTPNotImplementedCommand extends FTPMessageSender implements
		FTPCommand {

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
