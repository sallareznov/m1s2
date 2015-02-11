package ftp.command;

import ftp.FTPClientConfiguration;
import ftp.FTPDatabase;
import ftp.FTPMessageSender;

public class FTPPassCommand extends FTPMessageSender implements FTPCommand {

    @Override
    public boolean accept(String[] request) {
	return (request[0].equals("PASS"));
    }

    @Override
    public void execute(FTPClientConfiguration currentSession, String[] request) {
	final String password = request[1];
	final String username = currentSession.getUsername();
	final FTPDatabase database = FTPDatabase.getInstance();
	if (password.equals(database.getAccounts().get(username))) {
	    sendDefaultCommand(230);
	    sendDefaultCommand(225);
	} else {
	    sendDefaultCommand(430);
	    sendDefaultCommand(220);
	}
    }

}
