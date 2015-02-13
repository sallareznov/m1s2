package ftp.command;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.StringTokenizer;

import ftp.FTPMessageSender;
import ftp.configuration.FTPClientConfiguration;

public class FTPPortCommand extends FTPMessageSender implements FTPCommand {

	@Override
	public boolean accept(String command) {
		return command.equals("PORT");
	}

	@Override
	public void execute(String argument,
			FTPClientConfiguration clientConfiguration) {
		final StringTokenizer tokenizer = new StringTokenizer(argument, ",");
		final String ipAddress = tokenizer.nextToken() + "."
				+ tokenizer.nextToken() + "." + tokenizer.nextToken() + "."
				+ tokenizer.nextToken();
		final int portFirstNumber = Integer.parseInt(tokenizer.nextToken());
		final int portSecondNumber = Integer.parseInt(tokenizer.nextToken());
		final int port = (portFirstNumber * 256) + portSecondNumber;
		sendCommandWithDefaultMessage(clientConfiguration.getConnection(), 200);
		try {
			final Socket dataSocket = new Socket(
					InetAddress.getByName(ipAddress), port);
			clientConfiguration.setDataSocket(dataSocket);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Error while opening the socket.");
		}
	}

}
