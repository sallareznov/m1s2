package ftp;

import java.io.IOException;
import java.net.ServerSocket;

public class FTPServer extends FTPMessageSender {

	private static final int DEFAULT_PORT = 1500;
	private ServerSocket _serverSocket;
	private int _nbClients;

	public FTPServer(int port) throws IOException {
		super();
		_serverSocket = new ServerSocket(port);
		_nbClients = 0;
	}

	public int getLocalPort() {
		return _serverSocket.getLocalPort();
	}

	public String getLocalAddress() {
		return _serverSocket.getInetAddress().getHostAddress();
	}
	
	public int getNbClients() {
		return _nbClients;
	}

	public void connectToClient() throws IOException {
		setConnection(_serverSocket.accept());
		_nbClients++;
	}

	public void closeServer() throws IOException {
		_serverSocket.close();
	}

	public static void main(String[] args) throws IOException {
		final FTPServer ftpServer = new FTPServer(DEFAULT_PORT);
		System.out.println("--> FTP server opened on " + ftpServer.getLocalAddress() + ", port " + ftpServer.getLocalPort());
		while (true) {
			ftpServer.connectToClient();
			System.out.println("--> New client connected on this server.");
			System.out.println("--> total clients : " + ftpServer.getNbClients() + ".");
			ftpServer.sendCommand(220);
			final FTPRequestHandler requestHandler = new FTPRequestHandler(ftpServer.getConnection());
			new Thread(requestHandler).start();
		}
	}

}
