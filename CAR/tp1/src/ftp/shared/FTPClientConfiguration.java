package ftp.configuration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

import ftp.FailedCwdException;

/**
 * Class representing the configuration of the client
 */
public class FTPClientConfiguration {

	private int _id;
	private String _username;
	private Date _beginning;
	private ServerSocket _serverSocket;
	private Socket _commandSocket;
	private Socket _dataSocket;
	private String _baseDirectory;
	private String _workingDirectory;
	private String _directorySeparator;
	private boolean _connected;

	/**
	 * constructs a client configuration
	 * 
	 * @param serverConfiguration
	 *            the server configuration
	 */
	public FTPClientConfiguration(FTPServerConfiguration serverConfiguration) {
		_id = serverConfiguration.getIdGenerator().incrementAndGet();
		_username = null;
		_beginning = new Date();
		_commandSocket = serverConfiguration.getConnection();
		_dataSocket = null;
		_baseDirectory = serverConfiguration.getBaseDirectory();
		_workingDirectory = serverConfiguration.getBaseDirectory();
		_directorySeparator = serverConfiguration.getDirectorySeparator();
	}

	public int getId() {
		return _id;
	}

	public void setConnected(boolean connected) {
		_connected = connected;
	}

	public boolean isConnected() {
		return _connected;
	}

	public String getDirectorySeparator() {
		return _directorySeparator;
	}

	public String getUsername() {
		return _username;
	}

	public void setUsername(String username) {
		_username = username;
	}

	public Date getBeginning() {
		return _beginning;
	}

	public Socket getCommandSocket() {
		return _commandSocket;
	}

	public String getBaseDirectory() {
		return _baseDirectory;
	}

	public String getWorkingDirectory() {
		return _workingDirectory;
	}

	public void goDown(String newLeaf) throws FileNotFoundException {
		final String newDirectory = _workingDirectory + _directorySeparator
				+ newLeaf;
		if (!new File(newDirectory).exists()) {
			throw new FileNotFoundException();
		}
		_workingDirectory += _directorySeparator + newLeaf;
	}

	public void goUp() throws FailedCwdException {
		final int parentDirectoryEndIndex = _workingDirectory
				.lastIndexOf(_directorySeparator);
		_workingDirectory = _workingDirectory.substring(0,
				parentDirectoryEndIndex + 1);
		if (!_workingDirectory.contains(_baseDirectory)) {
			throw new FailedCwdException();
		}
	}

	public Socket getDataSocket() {
		return _dataSocket;
	}

	public ServerSocket getDataServerSocket() {
		return _serverSocket;
	}

	public void setDataServerSocket(ServerSocket serverSocket) {
		_serverSocket = serverSocket;
	}

	public void setDataSocket(Socket dataSocket) {
		_dataSocket = dataSocket;
	}

	public void closeDataSocket() throws IOException {
		_dataSocket.close();
	}

}
