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

	public int getId() {
		return id;
	}

	public void setConnected(boolean connected) {
		this.connected = connected;
	}

	public boolean isConnected() {
		return connected;
	}

	public String getDirectorySeparator() {
		return directorySeparator;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getBeginning() {
		return beginning;
	}

	public Socket getCommandSocket() {
		return commandSocket;
	}

	public String getBaseDirectory() {
		return baseDirectory;
	}

	public String getWorkingDirectory() {
		return workingDirectory;
	}

	public void goDown(String newLeaf) throws FileNotFoundException {
		final String newDirectory = workingDirectory + directorySeparator
				+ newLeaf;
		if (!new File(newDirectory).exists()) {
			throw new FileNotFoundException();
		}
		workingDirectory += directorySeparator + newLeaf;
	}

	public void goUp() throws FailedCwdException {
		final int parentDirectoryEndIndex = workingDirectory
				.lastIndexOf(directorySeparator);
		workingDirectory = workingDirectory.substring(0,
				parentDirectoryEndIndex + 1);
		if (!workingDirectory.contains(baseDirectory)) {
			throw new FailedCwdException();
		}
	}

	public Socket getDataSocket() {
		return dataSocket;
	}

	public ServerSocket getDataServerSocket() {
		return serverSocket;
	}

	public void setDataServerSocket(ServerSocket serverSocket) {
		this.serverSocket = serverSocket;
	}

	public void setDataSocket(Socket dataSocket) {
		this.dataSocket = dataSocket;
	}

	public void closeDataSocket() throws IOException {
		dataSocket.close();
	}

}
