package ftp.command;

import ftp.FTPMessageSender;
import ftp.configuration.FTPClientConfiguration;

public class FTPUserCommand extends FTPMessageSender implements FTPCommand {

	@Override
	public boolean accept(String[] requestTokens) {
		return requestTokens[0].equals("USER");
	}

	@Override
	public void execute(String[] requestTokens, FTPClientConfiguration clientConfiguration) {
		clientConfiguration.setUsername(requestTokens[1]);
		sendCommandWithDefaultMessage(clientConfiguration.getConnection(), 331);
	}

}
