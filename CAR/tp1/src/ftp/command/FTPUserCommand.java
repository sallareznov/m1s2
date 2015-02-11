package ftp.command;

import ftp.FTPClientConfiguration;
import ftp.FTPMessageSender;

public class FTPUserCommand extends FTPMessageSender implements FTPCommand {

    @Override
    public boolean accept(String[] request) {
	return (request[0].equals("USER"));
    }

    @Override
    public void execute(FTPClientConfiguration currentSession, String[] request) {
	currentSession.setUsername(request[1]);
	sendDefaultCommand(331);
    }

}
