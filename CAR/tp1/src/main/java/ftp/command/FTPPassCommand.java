package ftp.command;

import java.net.Socket;

import ftp.FTPDatabase;
import ftp.FTPMessageSender;
import ftp.configuration.FTPClientConfiguration;

public class FTPPassCommand extends FTPMessageSender implements FTPCommand {

    @Override
    public boolean accept(String command) {
	return command.equals("PASS");
    }

    @Override
    public void execute(String argument, FTPClientConfiguration clientConfiguration) {
	final String username = clientConfiguration.getUsername();
	final Socket connection = clientConfiguration.getConnection();
	final FTPDatabase database = FTPDatabase.getInstance();
	if (argument.equals(database.getAccounts().get(username))) {
	    sendCommandWithDefaultMessage(connection, 230);
	    sendCommandWithDefaultMessage(connection, 225);
	    //new FTPSystCommand().execute(requestTokens, clientConfiguration);
	} else {
	    sendCommandWithDefaultMessage(connection, 430);
	    sendCommandWithDefaultMessage(connection, 220);
	}
    }

}
