package ftp.shared;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

import ftp.server.util.FailedCwdException;

/**
 * Class representing the configuration of the client
 */
public class FTPClientConfiguration {

	private int id;
	private String username;
	private Date beginning;
	private ServerSocket serverSocket;
	private Socket commandSocket;
	private Socket dataSocket;
	private String baseDirectory;
	private String workingDirectory;
	private String directorySeparator;
	private boolean connected;

	/**
	 * constructs a client configuration
	 * 
	 * @param serverConfiguration
	 *            the server configuration
	 */
	public FTPClientConfiguration(FTPServerConfiguration serverConfiguration) {
		id = serverConfiguration.getIdGenerator().incrementAndGet();
		username = null;
		beginning = new Date();
		commandSocket = serverConfiguration.getConnection();
		dataSocket = null;
		baseDirectory = serverConfiguration.getBaseDirectory();
		workingDirectory = serverConfiguration.getBaseDirectory();
		directorySeparator = serverConfiguration.getDirectorySeparator();
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * sets the connection state
	 * @param connected
	 */
	public void setConnected(boolean connected) {
		this.connected = connected;
	}

	/**
	 * @return <code>true</code> if the client is connected
	 */
	public boolean isConnected() {
		return connected;
	}

	/**
	 * @return the directory separator
	 */
	public String getDirectorySeparator() {
		return directorySeparator;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * sets the username
	 * @param username the new username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the beginning of the connection (date)
	 */
	public Date getBeginning() {
		return beginning;
	}

	/**
	 * @return the command socket
	 */
	public Socket getCommandSocket() {
		return commandSocket;
	}

	/**
	 * @return the base directory
	 */
	public String getBaseDirectory() {
		return baseDirectory;
	}

	/**
	 * @return the working directory
	 */
	public String getWorkingDirectory() {
		return workingDirectory;
	}

	/**
	 * change the working directory with one of its subdirectories
	 * @param newLeaf the chosen directory
	 * @throws FileNotFoundException if the directory doesn't exist
	 */
	public void goDown(String newLeaf) throws FileNotFoundException {
		final String newDirectory = workingDirectory + directorySeparator
				+ newLeaf;
		if (!new File(newDirectory).exists()) {
			throw new FileNotFoundException();
		}
		workingDirectory += directorySeparator + newLeaf;
	}

	/**
	 * change the directory with the parent directory
	 * @throws FailedCwdException if we are above the base directory of the server
	 */
	public void goUp() throws FailedCwdException {
		int parentDirectoryEndIndex = workingDirectory
				.lastIndexOf(directorySeparator);
		if (parentDirectoryEndIndex == -1) {
			parentDirectoryEndIndex = 0;
		}
		workingDirectory = workingDirectory.substring(0,
				parentDirectoryEndIndex + 1);
		if (!workingDirectory.contains(baseDirectory)) {
			throw new FailedCwdException();
		}
	}

	/**
	 * @return the data socket
	 */
	public Socket getDataSocket() {
		return dataSocket;
	}
	
	/**
	 * sets the data socket
	 * @param dataSocket the new data socket
	 */
	public void setDataSocket(Socket dataSocket) {
		this.dataSocket = dataSocket;
	}

	/**
	 * @return the data server socket
	 */
	public ServerSocket getDataServerSocket() {
		return serverSocket;
	}

	/**
	 * sets the data server socket
	 * @param serverSocket the new data server socket
	 */
	public void setDataServerSocket(ServerSocket serverSocket) {
		this.serverSocket = serverSocket;
	}

	/**
	 * closes the data socket
	 * @throws IOException if an I/O error occurs
	 */
	public void closeDataSocket() throws IOException {
		dataSocket.close();
	}

}
