package ftp.command;

import ftp.FTPMessageSender;
import ftp.configuration.FTPClientConfiguration;

public class FTPCwdCommand extends FTPMessageSender implements FTPCommand {

	@Override
	public boolean accept(String[] requestTokens) {
		return requestTokens[0].equals("CWD");
	}

	@Override
	public void execute(String[] requestTokens,
			FTPClientConfiguration clientConfiguration) {
		// TODO Auto-generated method stub

	}

}
