package ftp.server.command;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.StringTokenizer;

import ftp.shared.FTPClientConfiguration;
import ftp.shared.FTPDatabase;
import ftp.shared.FTPRequest;

/**
 * Class representing the PORT command
 */
public class FTPPortCommand extends FTPConnectionNeededCommand {
	
	/**
	 * constructs a PORT command
	 * @param database the database
	 */
	public FTPPortCommand(FTPDatabase database) {
		super(database, "PORT");
	}
	
	@Override
	public boolean isValid(FTPRequest request) {
		return request.getLength() == 2;
	}

	@Override
	public void executeConnectionNeededCommand(FTPRequest request,
			FTPClientConfiguration clientConfiguration) throws IOException {
		final Socket connection = clientConfiguration.getCommandSocket();
		final StringTokenizer tokenizer = new StringTokenizer(request.getArgument(), ",");
		final String ipAddress = tokenizer.nextToken() + "."
				+ tokenizer.nextToken() + "." + tokenizer.nextToken() + "."
				+ tokenizer.nextToken();
		final int portFirstNumber = Integer.parseInt(tokenizer.nextToken());
		final int portSecondNumber = Integer.parseInt(tokenizer.nextToken());
		final int port = (portFirstNumber * 256) + portSecondNumber;
		sendCommand(connection, 200, getName());
		try {
			final Socket dataSocket = new Socket(
					InetAddress.getByName(ipAddress), port);
			clientConfiguration.setDataSocket(dataSocket);
		} catch (IOException e) {
			sendCommand(connection, 500);
		}
	}

}
