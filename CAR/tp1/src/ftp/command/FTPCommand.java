package ftp.command;

import ftp.FTPClientConfiguration;

public interface FTPCommand {
    
    boolean accept(String[] request);
    
    void execute(FTPClientConfiguration currentSession, String[] request);

}
