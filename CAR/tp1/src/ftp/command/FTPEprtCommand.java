package ftp.command;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.StringTokenizer;

import ftp.FTPDatabase;
import ftp.FTPMessageSender;
import ftp.FTPRequest;
import ftp.configuration.FTPClientConfiguration;

public class FTPEprtCommand extends FTPMessageSender implements FTPCommand {

	public FTPEprtCommand(FTPDatabase database) {
		super(database);
	}

	@Override
	public boolean accept(FTPRequest request) {
		final String command = request.getCommand();
		return command.equals("EPRT");
	}
	
	@Override
	public boolean isValid(FTPRequest request) {
		return (request.getLength() == 2);
	}
	
	@Override
	public void execute(FTPRequest request,
			FTPClientConfiguration clientConfiguration) {
		if (!isValid(request)) {
			sendCommand(clientConfiguration.getCommandSocket(), 501);
			return;
		}
		if (!clientConfiguration.isConnected()) {
			sendCommand(clientConfiguration.getCommandSocket(), 530);
			return;
		}
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
