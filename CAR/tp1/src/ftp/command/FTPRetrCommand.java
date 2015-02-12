package ftp.command;

import ftp.FTPMessageSender;
import ftp.configuration.FTPClientConfiguration;

public class FTPRetrCommand extends FTPMessageSender implements FTPCommand {

    @Override
    public boolean accept(String command) {
	return command.equals("RETR");
    }

    @Override
    public void execute(String argument, FTPClientConfiguration clientConfiguration) {
	// TODO Auto-generated method stub

    }

}
