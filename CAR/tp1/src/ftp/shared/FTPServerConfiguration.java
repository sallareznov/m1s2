package ftp.shared;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * The configuration of the server
 */
public class FTPServerConfiguration {

	private ServerSocket serverSocket;
	private int port;
	private Socket connection;
	private String baseDirectory;
	private AtomicInteger idGenerator;
	private String directorySeparator;

	/**
	 * Constructor
	 * @param port the port the server is listening on
	 * @param baseDirectory the base directory
	 * @throws IOException if an I/O error occurs
	 */
	public FTPServerConfiguration(int port, String baseDirectory) throws IOException {
		this.port = port;
		serverSocket = new ServerSocket(port);
		this.baseDirectory = new File(baseDirectory).getAbsolutePath();
		idGenerator = new AtomicInteger();
		directorySeparator = System.getProperty("file.separator");
		connection = null;
	}

	/**
	 * @return the address of the server
	 */
	public String getAddress() {
		return serverSocket.getInetAddress().getHostAddress();
	}

	/**
	 * @return the directory separator
	 */
	public String getDirectorySeparator() {
		return directorySeparator;
	}

	/**
	 * @return the id generator for the potential clients
	 */
	public AtomicInteger getIdGenerator() {
		return idGenerator;
	}

	/**
	 * @return the port
	 */
	public int getPort() {
		return port;
	}

	/**
	 * @return the server socket
	 */
	public ServerSocket getServerSocket() {
		return serverSocket;
	}

	/**
	 * @return the base directory
	 */
	public String getBaseDirectory() {
		return baseDirectory;
	}

	/**
	 * @return the connection
	 */
	public Socket getConnection() {
		return connection;
	}

	/**
	 * sets the connection
	 * @param connection the new connection
	 */
	public void setConnection(Socket connection) {
		this.connection = connection;
	}

}
