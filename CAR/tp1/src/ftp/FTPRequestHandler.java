package ftp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class FTPRequestHandler extends FTPMessageSender implements Runnable {

	private String _user;

	public FTPRequestHandler(Socket connexion) throws IOException {
		super();
		setConnexion(connexion);
	}

	private void processUser(String user) throws IOException {
		_user = user;
		answer(331);
	}

	private void processPass(String password) throws IOException {
		final FTPDatabase database = getDatabase();
		if (database.getAccounts().get(_user).equals(password)) {
			answer(230);
			answer(225);
		}
		else {
			answer(430);
			answer(220);
		}
	}
	
	private void processList() throws IOException {
		answer(213);
	}
	
	private void processRetr(String filename) {
		
	}
	
	private void processStor(String filename) {
		
	}
	
	private void processQuit() throws IOException {
		answer(231);
	}
	
	private void processCommandNotImplemented() throws IOException {
		answer(502);
	}
	
	private void processCwd(String directory) throws IOException {
		answer(250);
	}
	
	private void processPwd() throws IOException {
		answer(200);
		answer(257);
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
										System.out.println("PHere");
										processPwd();
									}
									else {
										System.out.println("CHere");
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
				final Socket connexion = getConnexion();
				final BufferedReader in = new BufferedReader(
						new InputStreamReader(connexion.getInputStream()));
				final String request = in.readLine();
				System.out.println(request);
				processRequest(request);
			}
		} catch (IOException e) {
			// TODO
		}
	}

}
