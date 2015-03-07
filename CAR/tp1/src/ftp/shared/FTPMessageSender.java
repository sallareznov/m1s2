package ftp;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.text.MessageFormat;

/**
 * @author diagne
 */
public abstract class FTPMessageSender {

	private FTPDatabase _database;

	/**
	 * Default constructor
	 * @param database the database
	 */
	public FTPMessageSender(FTPDatabase database) {
		_database = database;
	}

	/**
	 * @return the database
	 */
	public FTPDatabase getDatabase() {
		return _database;
	}

	/**
	 * sends the command via the command socket
	 * @param connection the connection (command socket)
	 * @param returnCode the return code
	 * @param arguments the arguments to pass to the formatter
	 */
	public void sendCommand(Socket connection, int returnCode,
			Object... arguments) {
		final String initialMessage = _database.getMessage(returnCode);
		final String formattedMessage = MessageFormat.format(initialMessage,
				arguments);
		writeCommandWithMessage(connection, returnCode, formattedMessage);
	}

	/**
	 * sends data via the data socket
	 * @param dataSocket the data socket
	 * @param message the data
	 */
	public void sendData(Socket dataSocket, String message) {
		try {
			final OutputStream outputStream = dataSocket.getOutputStream();
			final DataOutputStream dataOutputStream = new DataOutputStream(
					outputStream);
			dataOutputStream.writeBytes(message);
			dataOutputStream.flush();
		} catch (IOException e) {
			System.out.println("ERROR while sending data");
		}
	}

	private void writeCommandWithMessage(Socket connection, int returnCode,
			String message) {
		try {
			final OutputStream outputStream = connection.getOutputStream();
			final DataOutputStream dataOutputStream = new DataOutputStream(
					outputStream);
			dataOutputStream.writeBytes(returnCode + " " + message);
			dataOutputStream.writeBytes("\r\n");
			dataOutputStream.flush();
		} catch (IOException e) {
			System.out.println("A client has logged out.");
		}
	}

}
