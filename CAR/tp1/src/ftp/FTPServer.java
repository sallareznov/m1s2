package ftp;

import java.io.IOException;
import java.net.ServerSocket;

import ftp.configuration.FTPServerConfiguration;

/**
 * Classe representing a FTP server
 */
public class FTPServer extends FTPMessageSender {

	/**
	 * @uml.property  name="_configuration"
	 * @uml.associationEnd  
	 */
	private FTPServerConfiguration _configuration;

	/**
	 * constructs a new FTP server
	 * @param port the listening port
	 * @param baseDirectory the base directory
	 * @param database the database
	 */
	public FTPServer(int port, String baseDirectory, FTPDatabase database) {
		super(database);
		_configuration = new FTPServerConfiguration(port, baseDirectory);
	}

	public FTPServerConfiguration getConfiguration() {
		return _configuration;
	}

	/**
	 * attempts to connect to a client
	 */
	public void connectToClient() {
		try {
			final ServerSocket serverSocket = _configuration.getServerSocket();
			_configuration.setConnection(serverSocket.accept());
		} catch (IOException e) {
			System.err.println("I/O error while waiting for a connection.");
		}
	}

	/**
	 * closes the server
	 */
	public void close() {
		try {
			final ServerSocket serverSocket = _configuration.getServerSocket();
			serverSocket.close();
		} catch (IOException e) {
			System.err.println("I/O exception while closing the socket.");
		}
	}

}
