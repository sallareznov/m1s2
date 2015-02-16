package ftp.configuration;

import java.net.Socket;
import java.util.Date;

public class FTPClientConfiguration {

	private int _id;
	private String _username;
	private Date _beginning;
	private Socket _connection;
	private Socket _dataSocket;
	private String _baseDirectory;
	private String _workingDirectory;
	private String _directorySeparator;

	public FTPClientConfiguration(FTPServerConfiguration serverConfiguration) {
		_id = serverConfiguration.getIdGenerator().incrementAndGet();
		_username = null;
		_beginning = new Date();
		_connection = serverConfiguration.getConnection();
		_dataSocket = null;
		_baseDirectory = serverConfiguration.getBaseDirectory();
		_workingDirectory = serverConfiguration.getBaseDirectory();
		_directorySeparator = serverConfiguration.getDirectorySeparator();
	}
	
	public int getId() {
		return _id;
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

	public Socket getConnection() {
		return _connection;
	}
	
	public String getBaseDirectory() {
		return _baseDirectory;
	}
	
	public String getWorkingDirectory() {
		return _workingDirectory;
	}
	
	public void setWorkingDirectory(String newLeaf) {
		_workingDirectory += _directorySeparator + newLeaf;
	}
	
	public Socket getDataSocket() {
		return _dataSocket;
	}
	
	public void setDataSocket(Socket dataSocket) {
		_dataSocket = dataSocket;
	}
	
}
