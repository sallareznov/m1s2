package ftp;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class FTPRequestHandler extends FTPMessageSender implements Runnable {

    private String _username;
    private Socket _dataSocket;
    private String _workingDirectory;
    private String _relativeDirectory;
    private FTPCommandManager _commandManager;

    public FTPRequestHandler(Socket connexion, String baseDirectory, FTPCommandManager commandManager) {
	setBaseDirectory(baseDirectory);
	setConnection(connexion);
	_workingDirectory = baseDirectory;
	_commandManager = commandManager;
    }

    private void processUser(String username) {
	_username = username;
	sendDefaultCommand(331);
    }

    private void processPass(String password) {
	final FTPDatabase database = FTPDatabase.getInstance();
	if (password.equals(database.getAccounts().get(_username))) {
	    sendDefaultCommand(230);
	    sendDefaultCommand(225);
	} else {
	    sendDefaultCommand(430);
	    sendDefaultCommand(220);
	}
    }

    private void processSyst() {
	sendDefaultCommand(215);
    }
    
    private void processPort(String port) {
	
    }

    private void processPasv() throws IOException {
	final StringBuilder pasvAnswer = new StringBuilder();
	final String ipAddress = FTPDatabase.LOCALHOST_ADDRESS.replace(".", ",");
	final int port = getConnection().getPort() + 1;
	pasvAnswer.append("Entering Passive Mode ");
	pasvAnswer.append("(" + ipAddress);
	pasvAnswer.append("," + port / 256);
	pasvAnswer.append("," + Integer.toHexString(port % 256) + ").");
	System.out.println(pasvAnswer);
	sendParameterizedCommand(227, pasvAnswer.toString());
	try {
	    _dataSocket = new Socket(getConnection().getInetAddress().getHostName(), port);
	} catch (Exception e) {
	    System.out.println("Putain");
	}
    }

    private void processList() throws IOException {
	sendDefaultCommand(213);
    }

    private void processRetr(String filename) {

    }

    private void processStor(String filename) {

    }

    private void processQuit() throws IOException {
	sendDefaultCommand(231);
    }

    private void processCommandNotImplemented() throws IOException {
	sendDefaultCommand(502);
    }

    private void processCwd(String directory) throws IOException {
	String newDirectoryPath = directory.replace("~", getBaseDirectory());
	if (_workingDirectory.endsWith("/")) {
	    newDirectoryPath = _workingDirectory + directory;
	} else {
	    newDirectoryPath = _workingDirectory + FTPDatabase.DIRECTORY_SEPARATOR + directory;
	}
	System.out.println("newDirectory = " + newDirectoryPath);
	final File newDirectory = new File(newDirectoryPath);
	if (!newDirectory.exists())
	    sendParameterizedCommand(550, "Requested action not taken. File or directory doesn't exist");
	else
	    sendParameterizedCommand(250, "CWD command successful");
    }

    private void processPwd() throws IOException {
	sendDefaultCommand(257);
    }

    public void processRequest(String request) throws IOException {
	final String[] requestTokens = request.split(" ");
	final String command = requestTokens[0];
	switch (command) {
	    case FTPDatabase.USER_COMMAND: {
		processUser(requestTokens[1]);
		break;
	    }
	    case FTPDatabase.PASS_COMMAND: {
		processPass(requestTokens[1]);
		break;
	    }
	    case FTPDatabase.PORT_COMMAND : {
		processPort(requestTokens[1]);
		break;
	    }
	    case FTPDatabase.PASV_COMMAND: {
		processPasv();
		break;
	    }
	    case FTPDatabase.SYST_COMMAND: {
		processSyst();
		break;
	    }
	    case FTPDatabase.LIST_COMMAND: {
		processList();
		break;
	    }
	    case FTPDatabase.RETR_COMMAND: {
		processRetr(requestTokens[1]);
		break;
	    }
	    case FTPDatabase.STOR_COMMAND: {
		processStor(requestTokens[1]);
		break;
	    }
	    case FTPDatabase.PWD_COMMAND: {
		processPwd();
		break;
	    }
	    case FTPDatabase.CWD_COMMAND: {
		processCwd(requestTokens[1]);
		break;
	    }
	    case FTPDatabase.CDUP_COMMAND: {
		processCwd("..");
		break;
	    }
	    case FTPDatabase.QUIT_COMMAND: {
		processQuit();
		break;
	    }
	    default: {
		processCommandNotImplemented();
		break;
	    }
	}

    }

    @Override
    public void run() {
	try {
	    while (true) {
		final Socket connection = getConnection();
		final BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		final String request = in.readLine();
		System.out.println("---> " + request);
		processRequest(request);
	    }
	} catch (IOException e) {
	    // TODO
	}
    }

}
