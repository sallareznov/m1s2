package ftp.server.command;

import java.io.IOException;

import ftp.shared.FTPClientConfiguration;
import ftp.shared.FTPDatabase;
import ftp.shared.FTPRequest;

public abstract class FTPConnectionNeededCommand extends FTPBasicCommand {

	public FTPConnectionNeededCommand(FTPDatabase database, String name) {
		super(database, name);
	}

	@Override
	public void executeValidCommand(FTPRequest request,
			FTPClientConfiguration clientConfiguration) throws IOException {
		if (!clientConfiguration.isConnected()) {
			sendCommand(clientConfiguration.getCommandSocket(), 530);
			return;
		}
		executeConnectionNeededCommand(request, clientConfiguration);
	}

	public abstract void executeConnectionNeededCommand(FTPRequest request,
			FTPClientConfiguration clientConfiguration) throws IOException;

}
