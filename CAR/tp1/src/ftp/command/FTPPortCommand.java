package ftp.command;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.StringTokenizer;

import ftp.FTPDatabase;
import ftp.FTPMessageSender;
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
	public boolean accept(String command) {
		return command.equals("PORT");
	}

	@Override
	public void execute(String argument,
			FTPClientConfiguration clientConfiguration) {
		final Socket connection = clientConfiguration.getConnection();
		if (!clientConfiguration.isConnected()) {
			sendCommandWithDefaultMessage(connection, 530);
			return;
		}
		final StringTokenizer tokenizer = new StringTokenizer(argument, ",");
		final String ipAddress = tokenizer.nextToken() + "."
				+ tokenizer.nextToken() + "." + tokenizer.nextToken() + "."
				+ tokenizer.nextToken();
		final int portFirstNumber = Integer.parseInt(tokenizer.nextToken());
		final int portSecondNumber = Integer.parseInt(tokenizer.nextToken());
		final int port = (portFirstNumber * 256) + portSecondNumber;
		sendCommandWithFormattedMessage(connection, 200, "PORT");
		try {
			final Socket dataSocket = new Socket(
					InetAddress.getByName(ipAddress), port);
			clientConfiguration.setDataSocket(dataSocket);
		} catch (IOException e) {
			sendCommandWithDefaultMessage(connection, 500);
		}
	}

}
