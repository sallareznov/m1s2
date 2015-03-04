package ftp.command;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

import ftp.FTPDatabase;
import ftp.FTPMessageSender;
import ftp.FTPRequest;
import ftp.configuration.FTPClientConfiguration;

public class FTPStorCommand extends FTPMessageSender implements FTPCommand {

	public FTPStorCommand(FTPDatabase database) {
		super(database);
	}

	@Override
	public boolean accept(FTPRequest request) {
		final String command = request.getCommand();
		return command.equals("STOR");
	}
	
	@Override
	public boolean isValid(FTPRequest request) {
		return (request.getLength() == 2);
	}

	@Override
	public void execute(FTPRequest request,
			FTPClientConfiguration clientConfiguration) {
		if (!isValid(request)) {
			sendCommand(clientConfiguration.getCommandSocket(), 501);
			return;
		}
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
			final String fullFilePath = clientConfiguration
					.getWorkingDirectory()
					+ clientConfiguration.getDirectorySeparator()
					+ request.getArgument();
			final FileOutputStream fileOutputStream = new FileOutputStream(
					fullFilePath);
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
			sendCommand(clientConfiguration.getCommandSocket(), 500);
		}
	}

}
