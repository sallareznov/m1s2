package ftp.server.command;

import java.io.IOException;

import ftp.shared.FTPClientConfiguration;
import ftp.shared.FTPDatabase;
import ftp.shared.FTPMessageSender;
import ftp.shared.FTPRequest;

public abstract class FTPBasicCommand extends FTPMessageSender implements
		FTPCommand {
	
	private String name;

	public FTPBasicCommand(FTPDatabase database, String name) {
		super(database);
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	@Override
	public boolean accept(FTPRequest request) {
		final String command = request.getCommand();
		return name.equals(command);
	}

	@Override
	public abstract boolean isValid(FTPRequest request);

	public boolean checkConnection(FTPClientConfiguration clientConfiguration) throws IOException {
		if (!clientConfiguration.isConnected()) {
			sendCommand(clientConfiguration.getCommandSocket(), 530);
			return false;
		}
		return true;
	}

	@Override
	public void execute(FTPRequest request,
			FTPClientConfiguration clientConfiguration) throws IOException {
		if (!isValid(request)) {
			sendCommand(clientConfiguration.getCommandSocket(), 501);
			return;
		}
		executeValidCommand(request, clientConfiguration);
	}

	public abstract void executeValidCommand(FTPRequest request,
			FTPClientConfiguration clientConfiguration) throws IOException;

}
