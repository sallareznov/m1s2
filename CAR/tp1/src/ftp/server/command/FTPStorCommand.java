package ftp.server.command;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

import ftp.shared.FTPClientConfiguration;
import ftp.shared.FTPDatabase;
import ftp.shared.FTPRequest;

/**
 * Class representing the STOR command
 */
public class FTPStorCommand extends FTPConnectionNeededCommand {

	/**
	 * Constructs a STOR command
	 * 
	 * @param database
	 *            the database
	 */
	public FTPStorCommand(FTPDatabase database) {
		super(database, "STOR");
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
		final File file = new File(request.getArgument());
		if (!file.exists()) {
			sendCommand(clientConfiguration.getCommandSocket(), 550);
			return;
		}
		if (file.isDirectory()) {
			sendCommand(clientConfiguration.getCommandSocket(), 450);
			return;
		}
		sendCommand(clientConfiguration.getCommandSocket(), 150);
		final Socket dataSocket = clientConfiguration.getDataSocket();
		final InputStream dataSocketStream = dataSocket.getInputStream();
		final String fullFilePath = clientConfiguration.getWorkingDirectory()
				+ clientConfiguration.getDirectorySeparator()
				+ request.getArgument();
		final FileOutputStream fileOutputStream = new FileOutputStream(
				fullFilePath);
		int data = 0;
		while ((data = dataSocketStream.read()) != -1) {
			fileOutputStream.write(data);
		}
		fileOutputStream.close();
		clientConfiguration.closeDataSocket();
		sendCommand(clientConfiguration.getCommandSocket(), 226);
	}

}
