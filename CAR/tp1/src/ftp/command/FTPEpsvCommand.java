package ftp.command;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

import ftp.FTPDatabase;
import ftp.FTPMessageSender;
import ftp.configuration.FTPClientConfiguration;

public class FTPEpsvCommand extends FTPMessageSender implements FTPCommand {

	public FTPEpsvCommand(FTPDatabase database) {
		super(database);
	}

	@Override
	public boolean accept(String command) {
		return command.equals("EPSV");
	}

	@Override
	public void execute(String argument,
			FTPClientConfiguration clientConfiguration) {
		if (!clientConfiguration.isConnected()) {
			sendCommandWithDefaultMessage(clientConfiguration.getConnection(), 530);
			return;
		}
		final int newPort = clientConfiguration.getConnection().getPort() + 1;
		sendCommandWithFormattedMessage(clientConfiguration.getConnection(), 229, newPort);
		try {
			final Socket dataSocket = new Socket(
					InetAddress.getByName("::1"), newPort);
			clientConfiguration.setDataSocket(dataSocket);
		} catch (IOException e) {
			sendCommandWithDefaultMessage(clientConfiguration.getConnection(), 500);
		}
	}

}
