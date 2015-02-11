package ftp;

import java.io.IOException;
import java.net.ServerSocket;

public class FTPServer extends FTPMessageSender {

    private FTPServerConfiguration _configuration;
    private static final int DEFAULT_PORT = 1500;
    private static final String DEFAULT_DIRECTORY = "/home/salla";
    private int _port;
    private ServerSocket _serverSocket;
    private int _nbClients;

    public FTPServer(int port, String baseDirectory) {
	try {
	    setBaseDirectory(baseDirectory);
	    _port = port;
	    _serverSocket = new ServerSocket(port);
	    _nbClients = 0;
	} catch (IOException e) {
	    System.err.println("I/O exception while opening the socket");
	}
    }

    public int getPort() {
	return _port;
    }

    public int getLocalPort() {
	return _serverSocket.getLocalPort();
    }

    public String getLocalAddress() {
	return _serverSocket.getInetAddress().getHostAddress();
    }

    public int getNbClients() {
	return _nbClients;
    }

    public void connectToClient() {
	try {
	    setConnection(_serverSocket.accept());
	    _nbClients++;
	}
	catch (IOException e) {
	    System.err.println("I/O exception while waiting for a connection");
	}
    }

    public void closeServer() {
	try {
	    _serverSocket.close();
	}
	catch (IOException e) {
	    System.err.println("I/O exception while c");
	}
    }

    public static void main(String[] args) {
	final FTPServer ftpServer = new FTPServer(DEFAULT_PORT, DEFAULT_DIRECTORY);
	final FTPCommandManager commandManager = new FTPCommandManager();
	
	System.out.println("--> FTP server opened on " + ftpServer.getLocalAddress() + ", port " + ftpServer.getLocalPort());
	while (true) {
	    ftpServer.connectToClient();
	    System.out.println("--> New client connected on this server.");
	    System.out.println("--> total clients : " + ftpServer.getNbClients() + ".");
	    ftpServer.sendDefaultCommand(220);
	    final FTPRequestHandler requestHandler = new FTPRequestHandler(ftpServer.getConnection(), ftpServer.getBaseDirectory(), commandManager);
	    new Thread(requestHandler).start();
	}
    }

}
