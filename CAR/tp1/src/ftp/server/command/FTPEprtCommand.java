package ftp.server.command;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.StringTokenizer;

import ftp.shared.FTPClientConfiguration;
import ftp.shared.FTPDatabase;
import ftp.shared.FTPRequest;

public class FTPEprtCommand extends FTPConnectionNeededCommand {

	public FTPEprtCommand(FTPDatabase database) {
		super(database, "EPRT");
	}
	
	@Override
	public boolean isValid(FTPRequest request) {
		return request.getLength() == 2;
	}
	
	@Override
	public void executeConnectionNeededCommand(FTPRequest request,
			FTPClientConfiguration clientConfiguration) throws IOException {
		final StringTokenizer tokenizer = new StringTokenizer(request.getArgument(), "|");
		tokenizer.nextToken();
		final String ipAddress = tokenizer.nextToken();
		final String portString = tokenizer.nextToken();
		sendCommand(clientConfiguration.getCommandSocket(), 229, portString);
		try {
			final Socket dataSocket = new Socket(
					InetAddress.getByName(ipAddress), Integer.parseInt(portString));
			clientConfiguration.setDataSocket(dataSocket);
		} catch (IOException e) {
			sendCommand(clientConfiguration.getCommandSocket(), 500);
		}
	}

}
