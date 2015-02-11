package ftp.command;

import ftp.FTPMessageSender;
import ftp.configuration.FTPClientConfiguration;

public class FTPPortCommand extends FTPMessageSender implements FTPCommand {

	@Override
	public boolean accept(String[] requestTokens) {
		return requestTokens[0].equals("PORT");
	}

	@Override
	public void execute(String[] requestTokens, FTPClientConfiguration clientConfiguration) {
		// TODO Auto-generated method stub

	}

}
