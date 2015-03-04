package ftp.command;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.StringTokenizer;

import ftp.FTPDatabase;
import ftp.FTPMessageSender;
import ftp.FTPRequest;
import ftp.configuration.FTPClientConfiguration;

/**
 * Class representing the PORT command
 */
public class FTPPortCommand extends FTPMessageSender implements FTPCommand {
	
	/**
	 * constructs a PORT command
	 * @param database the database
	 */
	public FTPPortCommand(FTPDatabase database) {
		super(database);
	}

	@Override
	public boolean accept(FTPRequest request) {
		final String command = request.getCommand();
		return command.equals("PORT");
	}
	
	@Override
	public boolean isValid(FTPRequest request) {
		return (request.getLength() == 2);
	}

	@Override
	public void execute(FTPRequest request,
			FTPClientConfiguration clientConfiguration) {
		final Socket connection = clientConfiguration.getCommandSocket();
		if (!isValid(request)) {
			sendCommand(connection, 501);
			return;
		}
		if (!clientConfiguration.isConnected()) {
			sendCommand(connection, 530);
			return;
		}
		final StringTokenizer tokenizer = new StringTokenizer(request.getArgument(), ",");
		final String ipAddress = tokenizer.nextToken() + "."
				+ tokenizer.nextToken() + "." + tokenizer.nextToken() + "."
				+ tokenizer.nextToken();
		final int portFirstNumber = Integer.parseInt(tokenizer.nextToken());
		final int portSecondNumber = Integer.parseInt(tokenizer.nextToken());
		final int port = (portFirstNumber * 256) + portSecondNumber;
		sendCommand(connection, 200, "PORT");
		try {
			final Socket dataSocket = new Socket(
					InetAddress.getByName(ipAddress), port);
			clientConfiguration.setDataSocket(dataSocket);
		} catch (IOException e) {
			sendCommand(connection, 500);
		}
	}

}
