package ftp.command;

import ftp.FTPMessageSender;
import ftp.configuration.FTPClientConfiguration;

public class FTPNotImplementedCommand extends FTPMessageSender implements
		FTPCommand {

	@Override
	public boolean accept(String[] requestTokens) {
		return true;
	}

	@Override
	public void execute(String[] requestTokens,
			FTPClientConfiguration clientConfiguration) {
		sendCommandWithDefaultMessage(clientConfiguration.getConnection(), 502);

	}

}
