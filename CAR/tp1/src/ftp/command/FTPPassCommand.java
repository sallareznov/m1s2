package ftp.command;

import java.net.Socket;

import ftp.FTPDatabase;
import ftp.FTPMessageSender;
import ftp.configuration.FTPClientConfiguration;

public class FTPPassCommand extends FTPMessageSender implements FTPCommand {

	@Override
	public boolean accept(String[] requestTokens) {
		return requestTokens[0].equals("PASS");
	}

	@Override
	public void execute(String[] requestTokens, FTPClientConfiguration clientConfiguration) {
		final String password = requestTokens[1];
		final String username = clientConfiguration.getUsername();
		final Socket connection = clientConfiguration.getConnection();
		final FTPDatabase database = FTPDatabase.getInstance();
		if (password.equals(database.getAccounts().get(username))) {
			sendCommandWithDefaultMessage(connection, 230);
			sendCommandWithDefaultMessage(connection, 225);
			new FTPSystCommand().execute(requestTokens, clientConfiguration);
		} else {
			sendCommandWithDefaultMessage(connection, 430);
			sendCommandWithDefaultMessage(connection, 220);
		}
	}

}
