package ftp.command;

import ftp.FTPMessageSender;
import ftp.configuration.FTPClientConfiguration;

public class FTPCdupCommand extends FTPMessageSender implements FTPCommand {

    @Override
    public boolean accept(String command) {
	return command.equals("CDUP");
    }

    @Override
    public void execute(String argument, FTPClientConfiguration clientConfiguration) {
	new FTPCwdCommand().execute("..", clientConfiguration);
    }

}
