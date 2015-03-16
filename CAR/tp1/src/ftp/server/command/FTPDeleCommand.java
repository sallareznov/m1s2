package ftp.server.command;

import java.io.File;
import java.io.IOException;

import ftp.shared.FTPClientConfiguration;
import ftp.shared.FTPDatabase;
import ftp.shared.FTPRequest;

/**
 * Class representing the DELE command
 */
public class FTPDeleCommand extends FTPConnectionNeededCommand {

	/**
	 * constructs a DELE-command
	 * 
	 * @param database
	 *            the database
	 */
	public FTPDeleCommand(FTPDatabase database) {
		super(database, "DELE");
	}
	
	@Override
	public boolean isValid(FTPRequest request) {
		return request.getLength() == 2;
	}

	@Override
	public void executeConnectionNeededCommand(FTPRequest request,
			FTPClientConfiguration clientConfiguration) throws IOException {
		if ("anonymous".equals(clientConfiguration.getUsername())) {
			sendCommand(clientConfiguration.getCommandSocket(), 532);
			return;
		}
		final String filename = clientConfiguration.getWorkingDirectory()
				+ clientConfiguration.getDirectorySeparator()
				+ request.getArgument();
		final File fileToDelete = new File(filename);
		if (fileToDelete.exists()) {
			fileToDelete.delete();
			sendCommand(clientConfiguration.getCommandSocket(), 250);
			return;
		}
		sendCommand(clientConfiguration.getCommandSocket(), 550);
	}

}
