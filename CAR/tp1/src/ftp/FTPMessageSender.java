package ftp;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class FTPMessageSender {
	
	private Socket _connection;
	
	public synchronized void sendCommand(int answerCode) throws IOException {
		final OutputStream outputStream = _connection.getOutputStream();
		final DataOutputStream dataOutputStream = new DataOutputStream(
				outputStream);
		final FTPDatabase database = FTPDatabase.getInstance();
		final String answerMessage = database.getMessage(answerCode);
		dataOutputStream.writeBytes(answerCode + " " + answerMessage);
		dataOutputStream.writeBytes("\r\n");
		dataOutputStream.flush();
	}
	
	public Socket getConnection() {
		return _connection;
	}
	
	public void setConnection(Socket connection) {
		_connection = connection;
	}

}
