package ftp.command;

import ftp.FTPRequest;
import ftp.configuration.FTPClientConfiguration;

/**
 * Interface representing a FTP command
 */
public interface FTPCommand {

    /**
     * @param request
     *            the request
     * @return <code>true</code> if the command accepts this request
     */
    boolean accept(FTPRequest request);
    
    /**
     * @param request
     *            the request
     * @return <code>true</code> if the request is valid by checking the validity of its argument
     */
    boolean isValid(FTPRequest request);

    /**
     * executes the command
     * 
     * @param request
     *            the request
     * @param clientConfiguration
     *            the configuration of the client
     */
    void execute(FTPRequest request, FTPClientConfiguration clientConfiguration);

}
