package ftp.server.command;

import java.io.IOException;

import ftp.shared.FTPClientConfiguration;
import ftp.shared.FTPDatabase;
import ftp.shared.FTPRequest;

/**
 * A connection needed command is a command which is valid and needs the
 * client to be connected in order to execute itself
 */
public abstract class FTPConnectionNeededCommand extends FTPBasicCommand {

	/**
	 * Constructor
	 * @param database the database
	 * @param name the name of the command
	 */
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

	/**
	 * executes the command when the criteria is satisfied
	 * @param request the request
	 * @param clientConfiguration the configuration of the client
	 * @throws IOException if an I/O error occurs
	 */
	public abstract void executeConnectionNeededCommand(FTPRequest request,
			FTPClientConfiguration clientConfiguration) throws IOException;

}
