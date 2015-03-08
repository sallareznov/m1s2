package ftp.server;

import java.io.IOException;
import java.net.ServerSocket;

import ftp.shared.FTPDatabase;
import ftp.shared.FTPMessageSender;
import ftp.shared.FTPServerConfiguration;

/**
 * Classe representing a FTP server
 */
public class FTPServer extends FTPMessageSender {

	private FTPServerConfiguration configuration;

	/**
	 * constructs a new FTP server
	 * 
	 * @param port
	 *            the listening port
	 * @param baseDirectory
	 *            the base directory
	 * @param database
	 *            the database
	 * @throws IOException 
	 */
	public FTPServer(int port, String baseDirectory, FTPDatabase database) throws IOException {
		super(database);
		configuration = new FTPServerConfiguration(port, baseDirectory);
	}

	/**
	 * @return the configuration of the server
	 */
	public FTPServerConfiguration getConfiguration() {
		return configuration;
	}

	/**
	 * attempts to connect to a client (blocks until a client is connected)
	 * @throws IOException 
	 */
	public void connectToClient() throws IOException {
		final ServerSocket serverSocket = configuration.getServerSocket();
		configuration.setConnection(serverSocket.accept());
	}

	/**
	 * closes the server
	 * 
	 * @throws IOException
	 */
	public void close() throws IOException {
		final ServerSocket serverSocket = configuration.getServerSocket();
		serverSocket.close();
	}

}
