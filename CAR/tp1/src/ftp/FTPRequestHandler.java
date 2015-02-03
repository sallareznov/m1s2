package ftp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class FTPRequestHandler extends FTPMessageSender implements Runnable {

	private String _user;

	public FTPRequestHandler(Socket connexion) throws IOException {
		super();
		setConnection(connexion);
	}

	private void processUser(String user) throws IOException {
		_user = user;
		sendCommand(331);
	}

	private void processPass(String password) throws IOException {
		final FTPDatabase database = FTPDatabase.getInstance();
		if (database.getAccounts().get(_user).equals(password)) {
			sendCommand(230);
			sendCommand(225);
		}
		else {
			sendCommand(430);
			sendCommand(220);
		}
	}
	
	private void processList() throws IOException {
		sendCommand(213);
	}
	
	private void processRetr(String filename) {
		
	}
	
	private void processStor(String filename) {
		
	}
	
	private void processQuit() throws IOException {
		sendCommand(231);
	}
	
	private void processCommandNotImplemented() throws IOException {
		sendCommand(502);
	}
	
	private void processCwd(String directory) throws IOException {
		sendCommand(250);
	}
	
	private void processPwd() throws IOException {
		sendCommand(200);
		sendCommand(257);
	}

	public void processRequest(String request) throws IOException {
		final String[] requestTokens = request.split(" ");
		final String command = requestTokens[0];
		System.out.println(command.length());
		if (command.equals("USER")) {
			processUser(requestTokens[1]);
		} else {
			if (command.equals("PASS")) {
				processPass(requestTokens[1]);
			} else {
				if (command.equals("LIST")) {
					processList();
				} else {
					if (command.equals("RETR")) {
						processRetr(requestTokens[1]);
					} else {
						if (command.equals("STOR")) {
							processStor(requestTokens[1]);
						} else {
							if (command.equals("QUIT")) {
								processQuit();
							} else {
								if (command.equals("CWD") || command.equals("CDUP")) {
									processCwd(requestTokens[1]);
								}
								else {
									if (command.equals("PWD")) {
										processPwd();
									}
									else {
										processCommandNotImplemented();
									}
								}
							}
						}
					}
				}
			}
		}
	}

	@Override
	public void run() {
		try {
			while (true) {
				final Socket connection = getConnection();
				final BufferedReader in = new BufferedReader(
						new InputStreamReader(connection.getInputStream()));
				final String request = in.readLine();
				System.out.println(request);
				processRequest(request);
			}
		} catch (IOException e) {
			// TODO
		}
	}

}
