package ftp;

import java.io.IOException;
import java.net.ServerSocket;

import ftp.configuration.FTPServerConfiguration;

public class FTPServer extends FTPMessageSender {

	private FTPServerConfiguration _configuration;

	public FTPServer(int port, String baseDirectory) {
		_configuration = new FTPServerConfiguration(port, baseDirectory);
	}

	public FTPServerConfiguration getConfiguration() {
		return _configuration;
	}

	public void connectToClient() {
		try {
			final ServerSocket serverSocket = _configuration.getServerSocket();
			_configuration.setConnection(serverSocket.accept());
		} catch (IOException e) {
			System.err.println("I/O error while waiting for a connection.");
		}
	}

	public void closeServer() {
		try {
			final ServerSocket serverSocket = _configuration.getServerSocket();
			serverSocket.close();
		} catch (IOException e) {
			System.err.println("I/O exception while closing the socket.");
		}
	}

}
