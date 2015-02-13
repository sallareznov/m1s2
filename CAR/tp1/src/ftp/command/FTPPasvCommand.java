package ftp.command;

import java.net.InetAddress;
import java.net.Socket;
import java.util.StringTokenizer;

import ftp.FTPDatabase;
import ftp.FTPMessageSender;
import ftp.configuration.FTPClientConfiguration;

public class FTPPasvCommand extends FTPMessageSender implements FTPCommand {

	@Override
	public boolean accept(String command) {
		return command.equals("PASV");
	}

	@Override
	public void execute(String argument,
			FTPClientConfiguration clientConfiguration) {
		final Object[] answerTokens = new String[6];
		final StringTokenizer tokenizer = new StringTokenizer(FTPDatabase.LOCALHOST_IP_ADDRESS, ".");
		for (int i = 0 ; i < 4 ; i++) {
			answerTokens[i] = tokenizer.nextToken();
		}
		final int port = clientConfiguration.getConnection().getLocalPort() + 1;
		answerTokens[4] = (port / 256) + "";
		answerTokens[5] = Integer.toHexString(port % 256);
		sendFormattedCommand(clientConfiguration.getConnection(), 227,
				answerTokens);
		try {
			final Socket dataSocket = new Socket(
					InetAddress.getByName(FTPDatabase.LOCALHOST_IP_ADDRESS),
					port);
			clientConfiguration.setDataSocket(dataSocket);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
