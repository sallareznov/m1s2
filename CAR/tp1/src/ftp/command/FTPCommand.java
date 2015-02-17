package ftp.command;

import ftp.configuration.FTPClientConfiguration;

/**
 * Interface representing a FTP command
 */
public interface FTPCommand {

	/**
	 * @param command the command
	 * @return <code>true</code> if the command accepts this command string
	 */
    boolean accept(String command);

    /**
     * executes the command
     * @param argument the argument of the command
     * @param clientConfiguration the configuration of the client
     */
    void execute(String argument, FTPClientConfiguration clientConfiguration);

}
