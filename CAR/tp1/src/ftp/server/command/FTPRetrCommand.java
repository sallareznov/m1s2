package ftp.server.command;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import ftp.shared.FTPClientConfiguration;
import ftp.shared.FTPDatabase;
import ftp.shared.FTPRequest;

/**
 * Class representing a RETR command
 */
public class FTPRetrCommand extends FTPConnectionNeededCommand {

	/**
	 * Constructs a RETR command
	 * 
	 * @param database
	 *            the database
	 */
	public FTPRetrCommand(FTPDatabase database) {
		super(database, "RETR");
	}

	@Override
	public boolean isValid(FTPRequest request) {
		return request.getLength() == 2;
	}

	@Override
	public void executeConnectionNeededCommand(FTPRequest request,
			FTPClientConfiguration clientConfiguration) throws IOException {
		final String filename = clientConfiguration.getWorkingDirectory()
				+ clientConfiguration.getDirectorySeparator()
				+ request.getArgument();
		final File file = new File(filename);
		if (!file.exists()) {
			sendCommand(clientConfiguration.getCommandSocket(), 550);
			return;
		}
		if (!file.isDirectory()) {
			sendCommand(clientConfiguration.getCommandSocket(), 450);
			return;
		}
		sendCommand(clientConfiguration.getCommandSocket(), 150);
		final InputStream inputStream = new FileInputStream(filename);
		int data = 0;
		final OutputStream dataOutputStream = clientConfiguration
				.getDataSocket().getOutputStream();
		while ((data = inputStream.read()) != -1) {
			dataOutputStream.write(data);
		}
		inputStream.close();
		dataOutputStream.close();
		sendCommand(clientConfiguration.getCommandSocket(), 226);
	}

}
