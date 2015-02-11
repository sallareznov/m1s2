package ftp.command;

import ftp.FTPClientConfiguration;
import ftp.FTPMessageSender;

public class FTPSystCommand extends FTPMessageSender implements FTPCommand {

    @Override
    public boolean accept(String[] request) {
	return (request[1].equals("SYST"));
    }

    @Override
    public void execute(FTPClientConfiguration currentSession, String[] request) {
	// TODO Auto-generated method stub
    }

}
