package ftp.command;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

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
			sendCommandWithDefaultMessage(clientConfiguration.getConnection(),
					530);
			return;
		}
		try {
//			sendCommandWithDefaultMessage(clientConfiguration.getConnection(),
//					150);
			final FileOutputStream outputStream = new FileOutputStream(argument);
			BufferedReader in = new BufferedReader(
			        new InputStreamReader(clientConfiguration.getDataSocket().getInputStream()));
	//		final InputStreamReader dataInputStream = new InputStreamReader(clientConfiguration.getDataSocket().getInputStream());
			int data = 0;
			while ((data = in.read()) != -1) {
				System.out.println("...");
				outputStream.write(data);
			}
			System.out.println("Really nigger ?");
			outputStream.close();
			clientConfiguration.closeDataSocket();
			sendCommandWithDefaultMessage(clientConfiguration.getConnection(),
					226);
		} catch (FileNotFoundException e) {
			sendCommandWithDefaultMessage(clientConfiguration.getConnection(),
					550);
		} catch (IOException e) {
			System.err.println("Cannot store " + argument);
		}
	}

}
