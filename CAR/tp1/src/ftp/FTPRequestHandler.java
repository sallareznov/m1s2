package ftp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.text.SimpleDateFormat;

import ftp.command.FTPCommandManager;
import ftp.configuration.FTPClientConfiguration;
import ftp.configuration.FTPServerConfiguration;

/**
 * Class representing a request handler
 */
public class FTPRequestHandler extends FTPMessageSender implements Runnable {

	private FTPClientConfiguration _clientConfiguration;
	private FTPCommandManager _commandManager;

	/**
	 * @param database
	 *            the database
	 * @param serverConfiguration
	 *            the configuration of the server
	 * @param commandManager
	 *            the command manager
	 */
	public FTPRequestHandler(FTPDatabase database,
			FTPServerConfiguration serverConfiguration,
			FTPCommandManager commandManager) {
		super(database);
		_clientConfiguration = new FTPClientConfiguration(serverConfiguration);
		_commandManager = commandManager;
	}

	@Override
	public void run() {
		try {
			final SimpleDateFormat dateFormatter = new SimpleDateFormat(
					"dd MMM yyy, HH:mm:ss");
			System.out
					.println("Connection initiated at "
							+ dateFormatter.format(_clientConfiguration
									.getBeginning()));
			final Socket connection = _clientConfiguration.getCommandSocket();
			final BufferedReader in = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			String request = null;
			while ((request = in.readLine()) != null) {
				System.out.println("#" + _clientConfiguration.getId()
						+ " ---> " + request);
				processRequest(new FTPRequest(request));
			}
		} catch (IOException e) {
			System.err
					.println("I/O error occurs when creating the input stream or reading a line. Maybe the socket is closed, or is not connected");
			e.printStackTrace();
		}
	}

	/**
	 * treatment following a request received
	 * 
	 * @param request
	 *            the request
	 */
	public synchronized void processRequest(FTPRequest request) {
		_commandManager.handle(request, _clientConfiguration);
	}

}
