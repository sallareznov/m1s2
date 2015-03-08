package ftp.shared;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicInteger;

public class FTPServerConfiguration {

	private ServerSocket serverSocket;
	private int port;
	private Socket connection;
	private String baseDirectory;
	private AtomicInteger idGenerator;
	private String directorySeparator;

	public FTPServerConfiguration(int port, String baseDirectory) throws IOException {
		this.port = port;
		serverSocket = new ServerSocket(port);
		this.baseDirectory = new File(baseDirectory).getAbsolutePath();
		idGenerator = new AtomicInteger();
		directorySeparator = System.getProperty("file.separator");
		connection = null;
	}

	public String getAddress() {
		return serverSocket.getInetAddress().getHostAddress();
	}

	public String getDirectorySeparator() {
		return directorySeparator;
	}

	public AtomicInteger getIdGenerator() {
		return idGenerator;
	}

	public int getPort() {
		return port;
	}

	public ServerSocket getServerSocket() {
		return serverSocket;
	}

	public String getBaseDirectory() {
		return baseDirectory;
	}

	public Socket getConnection() {
		return connection;
	}

	public void setConnection(Socket connection) {
		this.connection = connection;
	}

}
