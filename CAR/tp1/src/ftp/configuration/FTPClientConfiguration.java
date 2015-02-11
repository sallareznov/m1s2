package ftp.configuration;

import java.net.Socket;
import java.util.Date;

public class FTPClientConfiguration {

	private String _username;
	private Date _beginning;
	private Socket _connection;
	private Socket _dataSocket;
	private String _workingDirectory;

	public FTPClientConfiguration(FTPServerConfiguration serverConfiguration) {
		_username = null;
		_beginning = new Date();
		_connection = serverConfiguration.getConnection();
		_dataSocket = null;
		_workingDirectory = serverConfiguration.getBaseDirectory();
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
	
	public String getWorkingDirectory() {
		return _workingDirectory;
	}
	
	public Socket getDataSocket() {
		return _dataSocket;
	}
	
	public void setDataSocket(Socket dataSocket) {
		_dataSocket = dataSocket;
	}
	
}
