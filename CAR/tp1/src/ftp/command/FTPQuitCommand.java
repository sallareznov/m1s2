package ftp.command;

import ftp.FTPClientConfiguration;
import ftp.FTPMessageSender;

public class FTPQuitCommand extends FTPMessageSender implements FTPCommand {

    @Override
    public boolean accept(String[] request) {
	// TODO Auto-generated method stub
	return false;
    }

    @Override
    public void execute(FTPClientConfiguration currentSession, String[] request) {
	// TODO Auto-generated method stub

    }

}
