package ftp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.text.SimpleDateFormat;

import ftp.command.FTPCommandManager;
import ftp.configuration.FTPClientConfiguration;
import ftp.configuration.FTPServerConfiguration;

public class FTPRequestHandler extends FTPMessageSender implements Runnable {

	private FTPClientConfiguration _clientConfiguration;
	private FTPCommandManager _commandManager;

	public FTPRequestHandler(FTPServerConfiguration serverConfiguration,
			FTPCommandManager commandManager) {
		_clientConfiguration = new FTPClientConfiguration(serverConfiguration);
		_commandManager = commandManager;
	}

	@Override
	public void run() {
		try {
			final SimpleDateFormat dateFormatter = new SimpleDateFormat(
					"dd MMM yyy, HH:mm:ss");
			System.out
					.println("Connection initiated at "
							+ dateFormatter.format(_clientConfiguration
									.getBeginning()));
			final Socket connection = _clientConfiguration.getConnection();
			final BufferedReader in = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			String request = null;
			while ((request = in.readLine()) != null) {
				System.out.println("#" + _clientConfiguration.getId()
						+ " ---> " + request);
				processRequest(new FTPRequest(request));
			}
		} catch (IOException e) {
			System.err
					.println("I/O error occurs when creating the input stream or reading a line. Maybe the socket is closed, or is not connected");
			e.printStackTrace();
		}
	}

	public synchronized void processRequest(FTPRequest request) {
		_commandManager.execute(request.getCommand(), request.getArgument(),
				_clientConfiguration);
	}
	
	// private void processCwd(String directory) throws IOException {
	// String newDirectoryPath = directory.replace("~", getBaseDirectory());
	// if (_workingDirectory.endsWith("/")) {
	// newDirectoryPath = _workingDirectory + directory;
	// } else {
	// newDirectoryPath = _workingDirectory
	// + FTPDatabase.DIRECTORY_SEPARATOR + directory;
	// }
	// System.out.println("newDirectory = " + newDirectoryPath);
	// final File newDirectory = new File(newDirectoryPath);
	// if (!newDirectory.exists())
	// sendParameterizedCommand(550,
	// "Requested action not taken. File or directory doesn't exist");
	// else
	// sendParameterizedCommand(250, "CWD command successful");
	// }
	//
	// private void processPwd() throws IOException {
	// sendDefaultCommand(257);
	// }

}
