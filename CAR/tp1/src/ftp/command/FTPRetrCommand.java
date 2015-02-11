package ftp.command;

import ftp.FTPMessageSender;
import ftp.configuration.FTPClientConfiguration;

public class FTPRetrCommand extends FTPMessageSender implements FTPCommand {

	@Override
	public boolean accept(String[] requestTokens) {
		return requestTokens[0].equals("RETR");
	}

	@Override
	public void execute(String[] requestTokens,
			FTPClientConfiguration clientConfiguration) {
		// TODO Auto-generated method stub

	}

}
