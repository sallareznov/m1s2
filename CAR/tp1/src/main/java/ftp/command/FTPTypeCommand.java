package ftp.command;

import ftp.FTPMessageSender;
import ftp.configuration.FTPClientConfiguration;

public class FTPTypeCommand extends FTPMessageSender implements FTPCommand {

	@Override
	public boolean accept(String command) {
		return (command.equals("TYPE"));
	}

	@Override
	public void execute(String argument,
			FTPClientConfiguration clientConfiguration) {
		sendCommandWithFormattedMessage(clientConfiguration.getConnection(), 200, "TYPE");
	}

}
