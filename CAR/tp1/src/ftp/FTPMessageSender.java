package ftp;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.text.MessageFormat;

public abstract class FTPMessageSender {

	public void sendFormattedCommand(Socket connection, int returnCode, Object... arguments) {
		final String initialMessage = FTPDatabase.getInstance().getMessage(
				returnCode);
		final String formattedMessage = MessageFormat.format(initialMessage,
				arguments);
		write(connection, returnCode, formattedMessage);
	}

	private void write(Socket connection, int returnCode, String message) {
		try {
			final OutputStream outputStream = connection.getOutputStream();
			final DataOutputStream dataOutputStream = new DataOutputStream(
					outputStream);
			dataOutputStream.writeBytes(returnCode + " " + message);
			dataOutputStream.writeBytes("\r\n");
			dataOutputStream.flush();
		} catch (IOException e) {
			System.out.println("ERROR while executing the command "
					+ returnCode);
		}
	}

	public void sendCommandWithDefaultMessage(Socket connection, int returnCode) {
		final FTPDatabase database = FTPDatabase.getInstance();
		final String message = database.getMessage(returnCode);
		write(connection, returnCode, message);
	}

	//
	// public void sendParameterizedCommand(int returnCode, String message)
	// throws IOException {
	// write(returnCode, message);
	// }

	public void sendData() {

	}

}
