package ftp.configuration;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicInteger;

public class FTPServerConfiguration {

	private ServerSocket _serverSocket;
	private int _port;
	private Socket _connection;
	private String _baseDirectory;
	private int _nbClients;
	private AtomicInteger _idGenerator;
	private String _directorySeparator;
	
	public FTPServerConfiguration(int port, String baseDirectory) {
		try {
			_port = port;
			_nbClients = 0;
			_serverSocket = new ServerSocket(port);
			_baseDirectory = baseDirectory;
			_idGenerator = new AtomicInteger();
			_directorySeparator = System.getProperty("file.separator");
		} catch (IOException e) {
			System.err.println("I/O error while opening the socket.");
		}
	}
	
	public String getAddress() {
		return _serverSocket.getInetAddress().getHostAddress();
	}
	
	public String getDirectorySeparator() {
		return _directorySeparator;
	}
	
	public AtomicInteger getIdGenerator() {
		return _idGenerator;
	}

	public int getPort() {
		return _port;
	}

	public int getNbClients() {
		return _nbClients;
	}
	
	public void addClient() {
		_nbClients++;
	}

	public ServerSocket getServerSocket() {
		return _serverSocket;
	}

	public String getBaseDirectory() {
		return _baseDirectory;
	}
	
	public Socket getConnection() {
		return _connection;
	}
	
	public void setConnection(Socket connection) {
		_connection = connection;
	}

}
