package ftp;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.text.MessageFormat;

public abstract class FTPMessageSender {

    private Socket _connection;
    private String _baseDirectory;

    public Socket getConnection() {
	return _connection;
    }

    public String getBaseDirectory() {
	return _baseDirectory;
    }

    public void setConnection(Socket connection) {
	_connection = connection;
    }

    public void setBaseDirectory(String baseDirectory) {
	_baseDirectory = baseDirectory;
    }

    public void sendFormattedCommand(int returnCode, Object ... arguments){
	final String initialMessage = FTPDatabase.getInstance().getMessage(returnCode);
	final String formattedMessage = MessageFormat.format(initialMessage, arguments);
	write(returnCode, formattedMessage);
    }
    private void write(int returnCode, String message) {
	try {
	    final OutputStream outputStream = _connection.getOutputStream();
	    final DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
	    dataOutputStream.writeBytes(returnCode + " " + message);
	    dataOutputStream.writeBytes("\r\n");
	    dataOutputStream.flush();
	} catch (IOException e) {
	    System.out.println("ERROR while executing the command " + returnCode);
	}
    }

    public void sendDefaultCommand(int returnCode) {
	final FTPDatabase database = FTPDatabase.getInstance();
	final String message = database.getMessage(returnCode);
	write(returnCode, message);
    }
//
//    public void sendParameterizedCommand(int returnCode, String message) throws IOException {
//	write(returnCode, message);
//    }

    public void sendData() {

    }

}
