package ftp.shared;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.text.MessageFormat;

/**
 * Type of a class capable of sending commands and data
 */
public abstract class FTPMessageSender {

	private FTPDatabase database;

	/**
	 * Default constructor
	 * 
	 * @param database
	 *            the database
	 */
	public FTPMessageSender(FTPDatabase database) {
		this.database = database;
	}

	/**
	 * @return the database
	 */
	public FTPDatabase getDatabase() {
		return database;
	}

	/**
	 * sends the command via the command socket
	 * 
	 * @param connection
	 *            the connection (command socket)
	 * @param returnCode
	 *            the return code
	 * @param arguments
	 *            the arguments to pass to the formatter
	 * @throws IOException if an I/O error occurs
	 */
	public void sendCommand(Socket connection, int returnCode,
			Object... arguments) throws IOException {
		final String initialMessage = database.getMessage(returnCode);
		final String formattedMessage = MessageFormat.format(initialMessage,
				(Object[]) arguments);
		writeCommandWithMessage(connection, returnCode, formattedMessage);
	}

	/**
	 * sends data via the data socket
	 * 
	 * @param dataSocket
	 *            the data socket
	 * @param message
	 *            the data
	 * @throws IOException
	 */
	public void sendData(Socket dataSocket, String message) throws IOException {
		final OutputStream outputStream = dataSocket.getOutputStream();
		final DataOutputStream dataOutputStream = new DataOutputStream(
				outputStream);
		dataOutputStream.writeBytes(message);
		dataOutputStream.flush();
	}

	private void writeCommandWithMessage(Socket connection, int returnCode,
			String message) throws IOException {
		final OutputStream outputStream = connection.getOutputStream();
		final DataOutputStream dataOutputStream = new DataOutputStream(
				outputStream);
		dataOutputStream.writeBytes(returnCode + " " + message);
		dataOutputStream.writeBytes("\r\n");
		dataOutputStream.flush();
	}

}
