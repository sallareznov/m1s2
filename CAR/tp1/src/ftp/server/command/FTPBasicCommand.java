package ftp.server.command;

import java.io.IOException;

import ftp.shared.FTPClientConfiguration;
import ftp.shared.FTPDatabase;
import ftp.shared.FTPMessageSender;
import ftp.shared.FTPRequest;

/**
 * A basic command checks the validity of a request before executing it
 */
public abstract class FTPBasicCommand extends FTPMessageSender implements
		FTPCommand {
	
	private String name;

	/**
	 * Constructs the basic command
	 * @param database the database
	 * @param name the name of the command
	 */
	public FTPBasicCommand(FTPDatabase database, String name) {
		super(database);
		this.name = name;
	}
	
	/**
	 * @return the name of the command
	 */
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

	/**
	 * checks if the client is connected
	 * @param clientConfiguration
	 * @return <code>true</code> if the client is connected, <code>false</code> otherwise
	 * @throws IOException if an I/O error occurs
	 */
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

	/**
	 * executes the valid command
	 * @param request the request
	 * @param clientConfiguration the configuration of the client
	 * @throws IOException if an I/O error occurs
	 */
	public abstract void executeValidCommand(FTPRequest request,
			FTPClientConfiguration clientConfiguration) throws IOException;

}
