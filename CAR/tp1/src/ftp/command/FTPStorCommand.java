package ftp.command;

import ftp.FTPMessageSender;
import ftp.configuration.FTPClientConfiguration;

public class FTPStorCommand extends FTPMessageSender implements FTPCommand {

    @Override
    public boolean accept(String command) {
	return command.equals("STOR");
    }

    @Override
    public void execute(String argument, FTPClientConfiguration clientConfiguration) {
	// TODO Auto-generated method stub

    }

}
