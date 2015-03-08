package ftp.server.command;

import java.io.IOException;

import ftp.shared.FTPClientConfiguration;
import ftp.shared.FTPDatabase;
import ftp.shared.FTPRequest;

/**
 * Class representing the SYST command
 */
public class FTPSystCommand extends FTPBasicCommand {
	
	/**
	 * constructs a SYST command
	 * @param database the database
	 */
	public FTPSystCommand(FTPDatabase database) {
		super(database, "SYST");
	}
	
	@Override
	public boolean isValid(FTPRequest request) {
		return request.getLength() == 1;
	}

	@Override
	public void executeValidCommand(FTPRequest request,
			FTPClientConfiguration clientConfiguration) throws IOException {
		sendCommand(clientConfiguration.getCommandSocket(), 215);
	}

}
