package ftp.server.command;

import java.io.IOException;

import ftp.shared.FTPClientConfiguration;
import ftp.shared.FTPRequest;

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
     * @throws IOException
     */
    void execute(FTPRequest request, FTPClientConfiguration clientConfiguration) throws IOException;

}
