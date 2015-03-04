package ftp.command;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

import ftp.FTPDatabase;
import ftp.FTPMessageSender;
import ftp.FTPRequest;
import ftp.configuration.FTPClientConfiguration;

public class FTPEpsvCommand extends FTPMessageSender implements FTPCommand {

	public FTPEpsvCommand(FTPDatabase database) {
		super(database);
	}

	@Override
	public boolean accept(FTPRequest request) {
		final String command = request.getCommand();
		return command.equals("EPSV");
	}
	
	@Override
	public boolean isValid(FTPRequest request) {
		return (request.getLength() == 1);
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
