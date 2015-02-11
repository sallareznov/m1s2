package ftp.command;

import ftp.FTPMessageSender;
import ftp.configuration.FTPClientConfiguration;

public class FTPSystCommand extends FTPMessageSender implements FTPCommand {

    @Override
    public boolean accept(String[] requestTokens) {
    	return requestTokens[0].equals("SYST");
    }

    @Override
    public void execute(String[] requestTokens, FTPClientConfiguration clientConfiguration) {
    	sendCommandWithDefaultMessage(clientConfiguration.getConnection(), 215);
    }

}
