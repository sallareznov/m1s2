package ftp;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.text.MessageFormat;

public abstract class FTPMessageSender {
	
	public void sendCommandWithDefaultMessage(Socket connection, int returnCode) {
		final FTPDatabase database = FTPDatabase.getInstance();
		final String message = database.getMessage(returnCode);
		writeCommandWithMessage(connection, returnCode, message);
	}

	public void sendCommandWithFormattedMessage(Socket connection, int returnCode, Object... arguments) {
		final String initialMessage = FTPDatabase.getInstance().getMessage(
				returnCode);
		final String formattedMessage = MessageFormat.format(initialMessage,
				arguments);
		writeCommandWithMessage(connection, returnCode, formattedMessage);
	}
	
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

	private void writeCommandWithMessage(Socket connection, int returnCode, String message) {
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
