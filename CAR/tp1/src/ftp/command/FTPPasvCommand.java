package ftp.command;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.StringTokenizer;

import ftp.FTPDatabase;
import ftp.FTPMessageSender;
import ftp.FTPRequest;
import ftp.configuration.FTPClientConfiguration;

/**
 * Class representing the PASV command
 */
public class FTPPasvCommand extends FTPMessageSender implements FTPCommand {

	/**
	 * constructs a PASV command
	 * 
	 * @param database
	 *            the database
	 */
	public FTPPasvCommand(FTPDatabase database) {
		super(database);
	}

	@Override
	public boolean accept(FTPRequest request) {
		final String command = request.getCommand();
		return command.equals("PASV");
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
		try {
			final ServerSocket dataServerSocket = new ServerSocket(0);
			clientConfiguration.setDataServerSocket(dataServerSocket);
			final int port = clientConfiguration.getDataServerSocket().getLocalPort();
			final String ipAddress = clientConfiguration.getCommandSocket().getInetAddress().getHostAddress();
			final Object[] answerTokens = new String[6];
			final StringTokenizer tokenizer = new StringTokenizer(ipAddress, ".");
			for (int i = 0 ; i < 4 ; i++) {
				answerTokens[i] = tokenizer.nextToken();
			}
			answerTokens[4] = (port / 256) + "";
			answerTokens[5] = Integer.toHexString(port % 256);
			sendCommand(clientConfiguration.getCommandSocket(),
					227, answerTokens);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*final Object[] answerTokens = new String[6];
		final String ipAddress = getDatabase().getHostAddress();
		final StringTokenizer tokenizer = new StringTokenizer(ipAddress, ".");
		for (int i = 0; i < 4; i++) {
			answerTokens[i] = tokenizer.nextToken();
		}
		int port = 21;
		answerTokens[4] = (port / 256) + "";
		answerTokens[5] = Integer.toHexString(port % 256);
		sendCommand(clientConfiguration.getConnection(),
				227, answerTokens);
		try {
			final Socket dataSocket = new Socket(
					InetAddress.getByName(ipAddress), port);
			clientConfiguration.setDataSocket(dataSocket);
		} catch (Exception e) {

		}*/
	}

}
