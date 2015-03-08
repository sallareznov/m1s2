package ftp.server.command;

import java.io.IOException;

import ftp.server.util.FailedCwdException;
import ftp.shared.FTPClientConfiguration;
import ftp.shared.FTPDatabase;
import ftp.shared.FTPRequest;

/**
 * Class representing a CDUP command
 */
public class FTPCdupCommand extends FTPConnectionNeededCommand {

	public FTPCdupCommand(FTPDatabase database) {
		super(database, "CDUP");
	}
	
	@Override
	public boolean isValid(FTPRequest request) {
		return request.getLength() == 1;
	}

	@Override
	public void executeConnectionNeededCommand(FTPRequest request,
			FTPClientConfiguration clientConfiguration) throws IOException {
		try {
			clientConfiguration.goUp();
			sendCommand(clientConfiguration.getCommandSocket(), 200, getName());
		} catch (FailedCwdException e) {
			sendCommand(clientConfiguration.getCommandSocket(), 550);
		}
	}

}
