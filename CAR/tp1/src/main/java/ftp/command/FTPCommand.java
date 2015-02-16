package ftp.command;

import ftp.configuration.FTPClientConfiguration;

public interface FTPCommand {

    boolean accept(String command);

    void execute(String argument, FTPClientConfiguration clientConfiguration);

}
