package ftp.command;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import ftp.FTPDatabase;
import ftp.FTPMessageSender;
import ftp.configuration.FTPClientConfiguration;

public class FTPRetrCommand extends FTPMessageSender implements FTPCommand {

	public FTPRetrCommand(FTPDatabase database) {
		super(database);
	}

	@Override
	public boolean accept(String command) {
		return command.equals("RETR");
	}

	@Override
	public void execute(String argument,
			FTPClientConfiguration clientConfiguration) {
		if (!clientConfiguration.isConnected()) {
			sendCommand(clientConfiguration.getCommandSocket(), 530);
			return;
		}
		final String filename = clientConfiguration.getBaseDirectory()
				+ clientConfiguration.getDirectorySeparator() + argument;
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
