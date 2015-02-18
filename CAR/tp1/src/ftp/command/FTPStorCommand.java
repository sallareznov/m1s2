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
		try {
			final FileOutputStream fileOutputStream = new FileOutputStream(
					argument);
			int data = 0;
			final Socket connection = clientConfiguration.getConnection();
			final InputStream connectionInputStream = clientConfiguration.getDataSocket()
					.getInputStream();
			while ((data = connectionInputStream.read()) != -1) {
				fileOutputStream.write(data);
			}
			fileOutputStream.close();
			sendCommandWithDefaultMessage(connection, 226);
		} catch (FileNotFoundException e) {
			sendCommandWithDefaultMessage(clientConfiguration.getConnection(),
					550);
		} catch (IOException e) {
			System.err.println("Cannot store " + argument);
		}
	}

}
