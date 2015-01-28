package ftp;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class FTPServer {

	private static final int DEFAULT_PORT = 1500;
	private ServerSocket _serverSocket;
	private Socket _connexion;
	private FTPUtils _utils;

	public FTPServer(int port) throws IOException {
		_serverSocket = new ServerSocket(port);
		_utils = new FTPUtils();
	}

	public int getLocalPort() {
		return _serverSocket.getLocalPort();
	}

	public String getLocalAddress() {
		return _serverSocket.getInetAddress().getHostAddress();
	}

	public void connect() throws IOException {
		_connexion = _serverSocket.accept();
	}

	public void closeServer() throws IOException {
		_serverSocket.close();
	}

	public Socket getConnexion() {
		return _connexion;
	}

	public void answer(int answerCode) throws IOException {
		final OutputStream outputStream = _connexion.getOutputStream();
		final DataOutputStream dataOutputStream = new DataOutputStream(
				outputStream);
		final String answerMessage = _utils.getMessage(answerCode);
		dataOutputStream.writeBytes(answerCode + " " + answerMessage);
		dataOutputStream.writeBytes("\r\n");
		dataOutputStream.flush();
	}

	public static void main(String[] args) throws IOException {
		final FTPServer ftpServer = new FTPServer(DEFAULT_PORT);
		while (true) {
			ftpServer.connect();
			System.out.println("New client connected on "
					+ ftpServer.getLocalAddress() + ", port "
					+ ftpServer.getLocalPort());
			ftpServer.answer(220);
			/*final FTPRequestHandler newRequest = new FTPRequestHandler(
					_connexion);
			newRequest.start();*/
			ftpServer.closeServer();
		}
	}

}
