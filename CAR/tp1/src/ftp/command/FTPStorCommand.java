package ftp.command;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

import ftp.FTPDatabase;
import ftp.FTPMessageSender;
import ftp.configuration.FTPClientConfiguration;

public class FTPStorCommand extends FTPMessageSender implements FTPCommand {

	public FTPStorCommand(FTPDatabase database) {
		super(database);
	}

	@Override
	public boolean accept(String command) {
		return command.equals("STOR");
	}

	@Override
	public void execute(String argument,
			FTPClientConfiguration clientConfiguration) {
		if (!clientConfiguration.isConnected()) {
			sendCommand(clientConfiguration.getCommandSocket(), 530);
			return;
		}
		if ("anonymous".equals(clientConfiguration.getUsername())) {
			sendCommand(clientConfiguration.getCommandSocket(), 532);
			return;
		}
		try {
			sendCommand(clientConfiguration.getCommandSocket(), 150);
			final Socket dataSocket = clientConfiguration.getDataSocket();
			final InputStream dataSocketStream = dataSocket.getInputStream();
			final String fullFilePath = clientConfiguration.getWorkingDirectory() + clientConfiguration.getDirectorySeparator() + argument;
			final FileOutputStream fileOutputStream = new FileOutputStream(fullFilePath);
			int data = 0;
			while ((data = dataSocketStream.read()) != -1) {
				fileOutputStream.write(data);
			}
			fileOutputStream.close();
			clientConfiguration.closeDataSocket();
			sendCommand(clientConfiguration.getCommandSocket(), 226);
		} catch (FileNotFoundException e) {
			sendCommand(clientConfiguration.getCommandSocket(), 550);
		} catch (IOException e) {
			System.err.println("Cannot store " + argument);
		}
	}

}
