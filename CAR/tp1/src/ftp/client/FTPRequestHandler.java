package ftp.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.text.SimpleDateFormat;

import org.apache.log4j.Logger;

import ftp.server.command.FTPCommandManager;
import ftp.shared.FTPClientConfiguration;
import ftp.shared.FTPDatabase;
import ftp.shared.FTPLoggerFactory;
import ftp.shared.FTPMessageSender;
import ftp.shared.FTPRequest;
import ftp.shared.FTPServerConfiguration;

/**
 * Class representing a request handler
 */
public class FTPRequestHandler extends FTPMessageSender implements Runnable {

	private FTPClientConfiguration clientConfiguration;
	private FTPCommandManager commandManager;
	private static final Logger LOGGER = FTPLoggerFactory
			.create(FTPRequestHandler.class);

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
		clientConfiguration = new FTPClientConfiguration(serverConfiguration);
		this.commandManager = commandManager;
	}

	@Override
	public void run() {
		try {
			final SimpleDateFormat dateFormatter = new SimpleDateFormat(
					"dd MMM yyy, HH:mm:ss");
			LOGGER.info("Connection initiated at "
					+ dateFormatter.format(clientConfiguration.getBeginning()));
			final Socket connection = clientConfiguration.getCommandSocket();
			final BufferedReader in = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			String request = null;
			while ((request = in.readLine()) != null) {
				LOGGER.info("#" + clientConfiguration.getId() + " ---> "
						+ request);
				processRequest(new FTPRequest(request));
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * treatment following a request received
	 * 
	 * @param request
	 *            the request
	 * @throws IOException 
	 */
	public synchronized void processRequest(FTPRequest request) throws IOException {
		commandManager.handle(request, clientConfiguration);
	}

}
