package ftp;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class FTPMessageSender {
	
	private Socket _connexion;
	private FTPDatabase _database;
	
	public FTPMessageSender() throws IOException {
		_database = new FTPDatabase();
	}
	
	public synchronized void answer(int answerCode) throws IOException {
		final OutputStream outputStream = _connexion.getOutputStream();
		final DataOutputStream dataOutputStream = new DataOutputStream(
				outputStream);
		final String answerMessage = _database.getMessage(answerCode);
		dataOutputStream.writeBytes(answerCode + " " + answerMessage);
		dataOutputStream.writeBytes("\r\n");
		dataOutputStream.flush();
	}
	
	public Socket getConnexion() {
		return _connexion;
	}
	
	public void setConnexion(Socket connexion) {
		_connexion = connexion;
	}
	
	public FTPDatabase getDatabase() {
		return _database;
	}

}
