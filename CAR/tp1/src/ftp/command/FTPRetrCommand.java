package ftp.command;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import ftp.FTPDatabase;
import ftp.FTPMessageSender;
import ftp.FTPRequest;
import ftp.configuration.FTPClientConfiguration;

public class FTPRetrCommand extends FTPMessageSender implements FTPCommand {

	public FTPRetrCommand(FTPDatabase database) {
		super(database);
	}

	@Override
	public boolean accept(FTPRequest request) {
		final String command = request.getCommand();
		return command.equals("RETR");
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
		final String filename = clientConfiguration.getBaseDirectory()
				+ clientConfiguration.getDirectorySeparator() + request.getArgument();
		try {
			sendCommand(clientConfiguration.getCommandSocket(), 150);
			final InputStream inputStream = new FileInputStream(filename);
			int data = 0;
			final OutputStream dataOutputStream = clientConfiguration.getDataSocket().getOutputStream();
			while ((data = inputStream.read()) != -1) {
				dataOutputStream.write(data);
			}
			inputStream.close();
			dataOutputStream.close();
			sendCommand(clientConfiguration.getCommandSocket(), 226);
		} catch (FileNotFoundException e) {
			sendCommand(clientConfiguration.getCommandSocket(), 550);
		} catch (IOException e) {
			System.out.println("Cannot send " + filename + " file to client !");
		}
	}

}
