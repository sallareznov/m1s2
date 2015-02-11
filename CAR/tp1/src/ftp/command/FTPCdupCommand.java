package ftp.command;

import ftp.FTPMessageSender;
import ftp.configuration.FTPClientConfiguration;

public class FTPCdupCommand extends FTPMessageSender implements FTPCommand {

	@Override
	public boolean accept(String[] requestTokens) {
		return requestTokens[0].equals("CDUP");
	}

	@Override
	public void execute(String[] requestTokens,
			FTPClientConfiguration clientConfiguration) {
		final String[] newTokens = { "CWD", ".." };
		new FTPCwdCommand().execute(newTokens, clientConfiguration);
	}

}
