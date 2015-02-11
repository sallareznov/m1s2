package ftp.command;

import ftp.configuration.FTPClientConfiguration;

public interface FTPCommand {
    
    boolean accept(String[] requestTokens);
    
    void execute(String[] requestTokens, FTPClientConfiguration clientConfiguration);

}
