package ftp.server.command;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

import ftp.shared.FTPClientConfiguration;
import ftp.shared.FTPDatabase;
import ftp.shared.FTPRequest;
 
/**
 * Class representing a EPSV command
 */
public class FTPEpsvCommand extends FTPConnectionNeededCommand {

	/**
	 * Constructs a EPSV command
	 * @param database the database
	 */
	public FTPEpsvCommand(FTPDatabase database) {
		super(database, "EPSV");
	}
	
	@Override
	public boolean isValid(FTPRequest request) {
		return request.getLength() == 1;
	}

	@Override
	public void executeConnectionNeededCommand(FTPRequest request,
			FTPClientConfiguration clientConfiguration) throws IOException {
		final int newPort = clientConfiguration.getCommandSocket().getPort() + 1;
		sendCommand(clientConfiguration.getCommandSocket(), 229, newPort);
		try {
			final Socket dataSocket = new Socket(
					InetAddress.getByName("::1"), newPort);
			clientConfiguration.setDataSocket(dataSocket);
		} catch (IOException e) {
			sendCommand(clientConfiguration.getCommandSocket(), 500);
		}
	}

}
