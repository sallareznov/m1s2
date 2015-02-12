package ftp.command;

import ftp.FTPMessageSender;
import ftp.configuration.FTPClientConfiguration;

public class FTPQuitCommand extends FTPMessageSender implements FTPCommand {

    @Override
    public boolean accept(String command) {
	return command.equals("QUIT");
    }

    @Override
    public void execute(String argument, FTPClientConfiguration clientConfiguration) {
	// TODO Auto-generated method stub

    }

}
