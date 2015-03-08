package ftp.server.command;

import java.io.IOException;
import java.util.StringTokenizer;

import ftp.shared.FTPClientConfiguration;
import ftp.shared.FTPDatabase;
import ftp.shared.FTPRequest;

/**
 * Class representing the PASV command
 */
public class FTPPasvCommand extends FTPConnectionNeededCommand {

	/**
	 * constructs a PASV command
	 * 
	 * @param database
	 *            the database
	 */
	public FTPPasvCommand(FTPDatabase database) {
		super(database, "PASV");
	}
	
	@Override
	public boolean isValid(FTPRequest request) {
		return request.getLength() == 1;
	}

	@Override
	public void executeConnectionNeededCommand(FTPRequest request,
			FTPClientConfiguration clientConfiguration) throws IOException {
			//clientConfiguration.setDataServerSocket(dataServerSocket);
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
	}

}
